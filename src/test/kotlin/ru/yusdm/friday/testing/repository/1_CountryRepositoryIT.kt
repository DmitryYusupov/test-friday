package ru.yusdm.friday.testing.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import ru.yusdm.friday.testing.entity.City
import ru.yusdm.friday.testing.entity.Country
import kotlin.test.assertTrue

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class `1_CountryRepositoryIT` : BaseIT() {

    @Autowired
    private lateinit var countryRepository: CountryRepository

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Test
    fun `should save country`() {
        //given
        val country = Country(id = 1L, name = "Russia", cities = mutableListOf())
        val cities = mutableListOf(
                City(id = 1, name = "Spb", country),
                City(id = 2, name = "Msc", country)
        )
        country.cities?.addAll(cities)

        //tested method invocation
        countryRepository.save(country)

        //assertions
        val actualCountry = testEntityManager.find(Country::class.java, 1L)
        assertTrue { actualCountry.id == 1L && actualCountry.name == "Russia" && actualCountry.cities?.size == 2 }
    }

    @Test
    fun `should save country 2`() {
        //given
        val country = Country(id = 1L, name = "USSR", cities = mutableListOf())
        val cities = mutableListOf(
                City(id = 1, name = "Spb", country),
                City(id = 2, name = "Msc", country)
        )
        country.cities?.addAll(cities)

        //tested method invocation
        countryRepository.save(country)

        //assertions
        val actualCountry = testEntityManager.find(Country::class.java, 1L)
        assertTrue { actualCountry.id == 1L && actualCountry.name == "USSR" && actualCountry.cities?.size == 2 }
    }
}