package ru.yusdm.friday.testing.person

class PersonService {

    fun savePerson(person: Person): Person {
        person.id = 1L
        return person
    }

    suspend fun savePersonSuspend(person: Person): Person {
        person.id = 1L
        return person
    }
}