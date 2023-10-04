package ru.yusdm.friday.testing.configuration

import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.util.backoff.FixedBackOff
import ru.yusdm.friday.testing.listener.CreateCountryKafkaEvent

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(KafkaProperties::class)
class KafkaConfiguration(private val kafkaProperties: KafkaProperties) {

    @Bean(COUNTRY_LISTENER_FACTORY)
    fun pplExportListenerFactory(): ConcurrentKafkaListenerContainerFactory<String, CreateCountryKafkaEvent> {
        return defaultContainerFactory<CreateCountryKafkaEvent>()
    }

    private inline fun <reified T : Any> defaultContainerFactory(consumerProps: Map<String, Any?>? = null): ConcurrentKafkaListenerContainerFactory<String, T> {
        return ConcurrentKafkaListenerContainerFactory<String, T>().apply {
            this.consumerFactory = defaultConsumerFactory<T>(consumerProps)
            setAckDiscarded(true)
            this.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
            setCommonErrorHandler(errorHandler())
        }
    }

    private inline fun <reified T> defaultConsumerFactory(extraProps: Map<String, Any?>?): DefaultKafkaConsumerFactory<String, T> {
        val props: MutableMap<String, Any?> = kafkaProperties.buildConsumerProperties()
        if (extraProps != null) {
            props.putAll(extraProps)
        }

        return DefaultKafkaConsumerFactory(props, StringDeserializer(), JsonDeserializer(T::class.java))
    }

    private fun errorHandler(): DefaultErrorHandler =
        DefaultErrorHandler(FixedBackOff(3000, 3))

    companion object {
        const val COUNTRY_LISTENER_FACTORY = "countryListenerFactory"
    }

}