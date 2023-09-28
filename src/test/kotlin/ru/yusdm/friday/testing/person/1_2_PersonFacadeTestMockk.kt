package ru.yusdm.friday.testing.person

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class `1_2_PersonFacadeTestMockk` {
    private val personService: PersonService = mockk()
    private val personFacade = PersonFacade(personService)

    @Test
    fun `shouldUpdate name`() {
        val personId = UUID.randomUUID()
        val personName = "PersonName"
        every {
            personService.updateName(
                personId = personId,
                newName = personName,
                databaseContext = allAny(),
                securityContext = allAny()
            )
        } returns Person(id = personId, name = personName)


        //tested method invocation
        val updatedPerson = personFacade.updateName(personId, personName)

        //assertions
        assertEquals(
            expected = Person(id = personId, name = personName),
            actual = updatedPerson
        )
    }

    @Test
    fun `shouldUpdate name with explicit params`() {
        val personId = UUID.randomUUID()
        val personName = "PersonName"
        every {
            personService.updateName(
                personId = personId,
                newName = personName,
                databaseContext = DatabaseContext(ctx = "dbContext"),
                securityContext = SecurityContext(ctx = "securityContext")
            )
        } returns Person(id = personId, name = personName)


        //tested method invocation
        val updatedPerson = personFacade.updateName(personId, personName)

        //assertions
        assertEquals(
            expected = Person(id = personId, name = personName),
            actual = updatedPerson
        )
    }
}