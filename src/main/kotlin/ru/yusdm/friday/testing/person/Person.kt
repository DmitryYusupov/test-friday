package ru.yusdm.friday.testing.person

import java.time.LocalDate

data class Person(
    var id: Long? = null,
    val name: String,
    val birthDate: LocalDate
)