package ru.yusdm.friday.testing.repository

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.yusdm.friday.testing.BaseIT
import ru.yusdm.friday.testing.entity.City
import ru.yusdm.friday.testing.entity.Country
import java.util.UUID
import kotlin.test.assertTrue

class CountryRepositorySpringBootTestIT: BaseIT() {

    @Autowired
    private lateinit var countryRepository: CountryRepository

    @BeforeEach
    fun beforeEach() {
        clearDatabase()
    }

    @Test
    fun `should save country`() {
        //given
        val countryId = UUID.randomUUID()
        val country = Country(id = countryId, name = "Russia", cities = mutableListOf())
        val cities = mutableListOf(
            City(id = UUID.randomUUID(), name = "Spb", country),
            City(id = UUID.randomUUID(), name = "Msc", country)
        )
        country.cities?.addAll(cities)

        //tested method invocation
        countryRepository.save(country)

        //assertions
        val actualCountry = transactionTemplate.execute {
            val c = entityManager.find(Country::class.java, countryId)!!
            c.cities?.size
            c
        }!!

        assertTrue { actualCountry.id == countryId && actualCountry.name == "Russia" && actualCountry.cities?.size == 2 }
    }

}