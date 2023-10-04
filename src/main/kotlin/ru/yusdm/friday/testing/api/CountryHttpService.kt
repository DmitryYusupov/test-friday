package ru.yusdm.friday.testing.api

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.yusdm.friday.testing.dto.CreateCountryRequestDTO
import ru.yusdm.friday.testing.listener.CreateCountryKafkaEvent

@Service
class CountryHttpService(private val kafkaTemplate: KafkaTemplate<String, CreateCountryKafkaEvent>) {

    fun create(createCountryRequestDTO: CreateCountryRequestDTO) {
        kafkaTemplate.send("country-test", CreateCountryKafkaEvent(countryName = createCountryRequestDTO.countryName))
    }

}