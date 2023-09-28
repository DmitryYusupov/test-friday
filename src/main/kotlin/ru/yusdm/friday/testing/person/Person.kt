package ru.yusdm.friday.testing.person

import java.time.LocalDate
import java.util.UUID

data class Person(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val dateOfBirth: LocalDate = LocalDate.now()
)