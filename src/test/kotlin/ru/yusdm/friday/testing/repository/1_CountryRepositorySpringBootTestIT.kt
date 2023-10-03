package ru.yusdm.friday.testing.repository

import jakarta.persistence.EntityManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.transaction.support.TransactionTemplate
import ru.yusdm.friday.testing.entity.City
import ru.yusdm.friday.testing.entity.Country
import kotlin.test.assertTrue

@SpringBootTest
class `1_CountryRepositorySpringBootTestIT` : BaseIT() {

    @Autowired
    private lateinit var countryRepository: CountryRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var transactionTemplate: TransactionTemplate

    @Autowired
    private lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    @BeforeEach
    fun beforeEach() {
        clearDatabase()
    }

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
        val actualCountry = transactionTemplate.execute {
            val c = entityManager.find(Country::class.java, 1L)!!
            c.cities?.size
            c
        }!!

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
        val actualCountry = transactionTemplate.execute {
            val c = entityManager.find(Country::class.java, 1L)!!
            c.cities?.size
            c
        }!!

        assertTrue { actualCountry.id == 1L && actualCountry.name == "USSR" && actualCountry.cities?.size == 2 }
    }

    @Test
    fun `should save country 3`() {
        //given
        val country = Country(id = 1L, name = "Russian empire", cities = mutableListOf())

        //tested method invocation
        transactionTemplate.execute {
            countryRepository.saveNative(country)
        }

        //assertions
        val actualCountry = transactionTemplate.execute {
            val c = entityManager.find(Country::class.java, 1L)!!
            c.cities?.size
            c
        }!!

        assertTrue { actualCountry.id == 1L && actualCountry.name == "Russian empire" }
    }

    private fun clearDatabase() {
        transactionTemplate.execute {
            namedParameterJdbcTemplate.update("DELETE FROM city", MapSqlParameterSource())
            namedParameterJdbcTemplate.update("DELETE FROM country", MapSqlParameterSource())
        }
    }

}