package ru.yusdm.friday.testing.person

import java.time.LocalDate
import java.util.UUID

class PersonService {

    fun save(person: Person): Person {
        return person
    }

    fun updateName(
        personId: UUID,
        newName: String,
        databaseContext: DatabaseContext,
        securityContext: SecurityContext
    ): Person {
        return Person(personId, newName)
    }

    fun getOldestPerson(): Person {
        return Person(
            id = UUID.randomUUID(),
            name = "PersonName",
            dateOfBirth = LocalDate.now()
        )
    }

}

data class DatabaseContext(val ctx: String)
data class SecurityContext(val ctx: String)