package ru.yusdm.friday.testing.person

class PersonService {

    fun save(person: Person): Person {
        return person.copy(id = 1)
    }

    fun updateFirstName(person: Person): Person {
        return person.copy(firstName = "UpdatedFirstName")
    }

    fun updateLastName(person: Person): Person {
        return person.copy(lastName = "UpdatedLastName")
    }

    fun updateAge(person: Person): Person {
        return person.copy(age = 1000)
    }
}