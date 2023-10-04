package ru.yusdm.friday.testing.listener

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import ru.yusdm.friday.testing.configuration.KafkaConfiguration
import ru.yusdm.friday.testing.service.CountryService

@Service
class CreateCountryKafkaEventListener(private val countryService: CountryService) {

    @KafkaListener(
        topics = ["country"],
        containerFactory = KafkaConfiguration.COUNTRY_LISTENER_FACTORY,
        groupId = "friday-kafka-group"
    )
    fun onCreateCountryEvent(@Payload createCountryKafkaEvent: CreateCountryKafkaEvent) {
        countryService.create(createCountryKafkaEvent)
    }
}