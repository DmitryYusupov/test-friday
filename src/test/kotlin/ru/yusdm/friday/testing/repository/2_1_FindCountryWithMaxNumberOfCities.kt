package ru.yusdm.friday.testing.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import ru.yusdm.friday.testing.entity.City
import ru.yusdm.friday.testing.entity.Country
import kotlin.test.assertEquals


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class `2_1_FindCountryWithMaxNumberOfCities` : BaseIT() {

    @Autowired
    private lateinit var countryRepository: CountryRepository

    @Test
    fun `should find country with max number cities`() {
        var countryId = 0L
        var cityId = 1L
        //given
        val country1 = Country(id = ++countryId, name = "Country_A", cities = mutableListOf()).also {
            val city1 = City(
                    id = ++cityId,
                    name = "City_$cityId",
                    country = it
            )
            it.cities?.add(city1)
        }

        val country2 = Country(id = ++countryId, name = "Country_B", cities = mutableListOf()).also {
            val city1 = City(
                    id = ++cityId,
                    name = "City_$cityId",
                    country = it
            )
            val city2 = City(
                    id = ++cityId,
                    name = "City_$cityId",
                    country = it
            )
            it.cities?.add(city1)
            it.cities?.add(city2)
        }

        val country3 = Country(id = ++countryId, name = "Country_C", cities = mutableListOf()).also {
            val city1 = City(
                    id = ++cityId,
                    name = "City_$cityId",
                    country = it
            )
            val city2 = City(
                    id = ++cityId,
                    name = "City_$cityId",
                    country = it
            )
            val city3 = City(
                    id = ++cityId,
                    name = "City_$cityId",
                    country = it
            )
            it.cities?.add(city1)
            it.cities?.add(city2)
            it.cities?.add(city3)
        }
        countryRepository.save(country1)
        countryRepository.save(country2)
        countryRepository.save(country3)

        //tested method invocation
        val actual = countryRepository.getCountryWithMaxNumberOfCities()

        //assertions
        assertEquals(actual = actual.name, expected = "Country_C")
    }
}