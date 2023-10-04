package ru.yusdm.friday.testing.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yusdm.friday.testing.api.CountryControllerRequestMapping.BASE_PATH
import ru.yusdm.friday.testing.api.CountryControllerRequestMapping.CREATE_COUNTRY
import ru.yusdm.friday.testing.dto.CreateCountryRequestDTO

object CountryControllerRequestMapping {
    const val BASE_PATH = "/api/country"
    const val CREATE_COUNTRY = ""
}

@RestController
@RequestMapping(BASE_PATH)
class CountryController(private val countryHttpService: CountryHttpService) {


    @PostMapping(CREATE_COUNTRY)
    fun createCountry(@RequestBody createCountryRequestDTO: CreateCountryRequestDTO) {
        countryHttpService.create(createCountryRequestDTO)
    }
}