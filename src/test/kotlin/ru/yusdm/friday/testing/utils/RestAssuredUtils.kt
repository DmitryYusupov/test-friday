package ru.yusdm.friday.testing.utils

import io.restassured.response.ResponseBodyExtractionOptions
import io.restassured.response.ValidatableResponse

inline fun <reified T : Any> ResponseBodyExtractionOptions.az(): T? = `as`(T::class.java)

inline fun <reified T : Any> ValidatableResponse.extractBodyOrNull(): T? {
    return this.extract().body()?.az()
}

inline fun <reified T : Any> ValidatableResponse.extractBody(): T {
    return this.extract().body().az()!!
}
