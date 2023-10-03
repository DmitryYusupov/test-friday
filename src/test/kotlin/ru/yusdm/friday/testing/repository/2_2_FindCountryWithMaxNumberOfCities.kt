package ru.yusdm.friday.testing.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import kotlin.test.assertEquals


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class `2_2_FindCountryWithMaxNumberOfCities` : BaseIT() {

    @Autowired
    private lateinit var countryRepository: CountryRepository

    @Test
    @Sql(scripts = ["classpath:/ru/yusdm/friday/testing/repository/findCountryWithMaxNumberOfCities.sql"])
    fun `should update name`() {
        //tested method invocation
        val actual = countryRepository.getCountryWithMaxNumberOfCities()

        //assertions
        assertEquals(actual = actual.name, expected = "Country_C")
    }
}