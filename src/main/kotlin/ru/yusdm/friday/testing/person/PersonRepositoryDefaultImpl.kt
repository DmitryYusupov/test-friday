package ru.yusdm.friday.testing.person

import org.springframework.stereotype.Repository

@Repository
class PersonRepositoryDefaultImpl: PersonRepository {

    private val storage = mutableListOf<Person>()

    override fun save(person: Person): Person {
        storage.add(person)
        return person
    }

    override fun getByIdOrNull(id: Long): Person? {
        return storage.firstOrNull { it.id == id }
    }

    override fun findAll(): List<Person> {
        return storage.toList()
    }
}