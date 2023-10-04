package ru.yusdm.friday.testing.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.yusdm.friday.testing.dto.CreateCountryRequestDTO
import ru.yusdm.friday.testing.listener.CreateCountryKafkaEvent

@Service
class CountryHttpService(
    private val kafkaTemplate: KafkaTemplate<String, CreateCountryKafkaEvent>,
    @Value("\${app.topicName}")
    private val topicName: String
) {

    fun create(createCountryRequestDTO: CreateCountryRequestDTO) {
        kafkaTemplate.send(topicName, CreateCountryKafkaEvent(countryName = createCountryRequestDTO.countryName))
    }

}