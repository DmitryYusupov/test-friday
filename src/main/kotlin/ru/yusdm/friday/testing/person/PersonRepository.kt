package ru.yusdm.friday.testing.person

interface PersonRepository {
    fun save(person: Person): Person

    fun getByIdOrNull(id: Long): Person?

    fun findAll(): List<Person>
}