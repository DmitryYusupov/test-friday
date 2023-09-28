package ru.yusdm.friday.testing.person

import java.util.UUID

data class Person(
    val id: UUID = UUID.randomUUID(),
    val name: String
)