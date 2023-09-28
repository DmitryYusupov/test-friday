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
        databaseContext: DatabaseContext = DatabaseContext("default"),
        securityContext: SecurityContext = SecurityContext("default")
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

    fun getLatestSyncSystemTime(): Long {
        return System.currentTimeMillis()
    }

}

data class DatabaseContext(val ctx: String)
data class SecurityContext(val ctx: String)