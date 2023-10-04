package ru.yusdm.friday.testing.api

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import ru.yusdm.friday.testing.BaseIT
import ru.yusdm.friday.testing.api.CountryControllerRequestMapping.BASE_PATH
import ru.yusdm.friday.testing.api.CountryControllerRequestMapping.CREATE_COUNTRY
import ru.yusdm.friday.testing.dto.CreateCountryRequestDTO
import ru.yusdm.friday.testing.entity.Country
import java.util.*
import kotlin.properties.Delegates
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryControllerIT : BaseIT() {

    @set:LocalServerPort
    var port: Int by Delegates.notNull()

    @BeforeEach
    fun beforeEach() {
        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns uid
    }

    @AfterEach
    fun afterEach() {
        unmockkStatic(UUID::class)
    }

    @Test
    fun `should create country`() {
        //val given
        val createCountryRequestDTO = CreateCountryRequestDTO(countryName = "New country")

        //tested method invocation
        doRequest()
            .contentType(ContentType.JSON)
            .body(createCountryRequestDTO)
            .`when`()
            .post(CREATE_COUNTRY)
            .then()
            .statusCode(HttpStatus.OK.value())
        waitKafkaHandledMessage()

        //assertions
        val saveCountry = transactionTemplate.execute { entityManager.find(Country::class.java, uid) }!!
        assertEquals(expected = "New country", actual = saveCountry.name)
    }

    private fun waitKafkaHandledMessage() {
        Thread.sleep(2000)
    }

    private val restSpec: RequestSpecification
        get() = given()
            .port(port)
            .basePath(BASE_PATH)

    private fun doRequest(): RequestSpecification {
        return restSpec
    }

    private companion object {
        val uid = UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3")
    }
}