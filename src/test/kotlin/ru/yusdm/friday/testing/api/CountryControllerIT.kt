package ru.yusdm.friday.testing.api

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import ru.yusdm.friday.testing.BaseIT
import ru.yusdm.friday.testing.api.CountryControllerRequestMapping.BASE_PATH
import ru.yusdm.friday.testing.api.CountryControllerRequestMapping.CREATE_COUNTRY
import ru.yusdm.friday.testing.dto.CreateCountryRequestDTO
import kotlin.properties.Delegates

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryControllerIT : BaseIT() {

    @set:LocalServerPort
    var port: Int by Delegates.notNull()

    @Test
    fun `should create country`() {
        doRequest().contentType(ContentType.JSON).body(
            CreateCountryRequestDTO(countryName = "New country")
        ).`when`().post(CREATE_COUNTRY)
            .then()
            .statusCode(HttpStatus.OK.value())
    }


    private val restSpec: RequestSpecification
        get() = given()
            .port(port)
            .basePath(BASE_PATH)

    private fun doRequest(): RequestSpecification {
        return restSpec
    }

}