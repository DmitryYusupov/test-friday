package ru.yusdm.friday.testing.person

import java.util.*

class PersonFacade(private val personService: PersonService) {

    fun updateName(personId: UUID, newName: String): Person {
        return personService.updateName(
            personId = personId,
            newName = newName,
            databaseContext = DatabaseContext(ctx = "dbContext"),
            securityContext = SecurityContext(ctx = "securityContext")
        )
    }

    fun updateNameWithDefaultValues(personId: UUID, newName: String): Person {
        return personService.updateName(personId = personId, newName = newName)
    }

    fun updateNameWithExtensionFunction(personId: UUID, newName: String): Person {
        return personService.updateNameExtension(
            personId = personId,
            newName = newName,
            databaseContext = DatabaseContext(ctx = "dbContext"),
            securityContext = SecurityContext(ctx = "securityContext")
        )
    }
}

fun PersonService.updateNameExtension(
    personId: UUID, newName: String,
    databaseContext: DatabaseContext,
    securityContext: SecurityContext
): Person {
    return this.updateName(personId, newName, databaseContext, securityContext)
}