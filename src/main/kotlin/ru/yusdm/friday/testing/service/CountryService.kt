package ru.yusdm.friday.testing.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.friday.testing.entity.Country
import ru.yusdm.friday.testing.listener.CreateCountryKafkaEvent
import ru.yusdm.friday.testing.repository.CountryRepository
import java.util.*

@Service
@Transactional
class CountryService(private val countryRepository: CountryRepository) {

    fun create(createCountryKafkaEvent: CreateCountryKafkaEvent): Country {
        val newCountry = Country(
            id = UUID.randomUUID(),
            name = createCountryKafkaEvent.countryName,
            cities = null
        )
        countryRepository.save(newCountry)

        return newCountry
    }
}