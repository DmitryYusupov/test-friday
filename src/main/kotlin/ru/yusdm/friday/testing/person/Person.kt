package ru.yusdm.friday.testing.person

import java.time.LocalDate

data class Person(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val birthDate: LocalDate
)

class Pet(
    val name: String,
    val age: Int
)