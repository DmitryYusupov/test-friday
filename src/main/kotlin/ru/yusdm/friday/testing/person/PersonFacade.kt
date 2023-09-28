package ru.yusdm.friday.testing.person

import java.util.*

class PersonFacade(private val personService: PersonService) {

    fun save(person: Person): Person {
        return personService.save(person)
    }

    fun updateName(
        personId: UUID,
        newName: String,
    ): Person {
        return personService.updateName(
            personId = personId,
            newName = newName,
            databaseContext = DatabaseContext(ctx = UUID.randomUUID().toString()),
            securityContext = SecurityContext(ctx = UUID.randomUUID().toString())
        )
    }
}