package ru.yusdm.friday.testing.person

class ComplexPerson(
    val id: Long? = null,
    val name: String,
    val pets: List<Pet>
) {
    data class Pet(val name: String)
}

