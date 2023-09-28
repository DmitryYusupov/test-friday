package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.util.*
import kotlin.test.assertEquals

class `1_1_PersonFacadeTestMockitoKotlin` {
    private val personService: PersonService = mock()
    private val personFacade = PersonFacade(personService)

    @Test
    fun `shouldUpdate name`() {
        val personId = UUID.randomUUID()
        val personName = "PersonName"
        given(
            personService.updateName(
                personId = personId,
                newName = personName,
                databaseContext = any(),
                securityContext = any()
            )
        ).willReturn(Person(id = personId, name = personName))

        //tested method invocation
        val updatedPerson = personFacade.updateName(personId, personName)

        //assertions
        assertEquals(
            expected = Person(id = personId, name = personName),
            actual = updatedPerson
        )
    }

    @Test
    fun `shouldUpdate name with matchers`() {
        val personId = UUID.randomUUID()
        val personName = "PersonName"
        given(
            personService.updateName(
                personId = eq(personId),
                newName = eq(personName),
                databaseContext = any(),
                securityContext = any()
            )
        ).willReturn(Person(id = personId, name = personName))

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
        given(
            personService.updateName(
                personId = personId,
                newName = personName,
                databaseContext = DatabaseContext(ctx = "dbContext"),
                securityContext = SecurityContext(ctx = "securityContext")
            )
        ).willReturn(Person(id = personId, name = personName))

        //tested method invocation
        val updatedPerson = personFacade.updateName(personId, personName)

        //assertions
        assertEquals(
            expected = Person(id = personId, name = personName),
            actual = updatedPerson
        )
    }

    @Test
    fun `shouldUpdate name with matchers using def values`() {
        val personId = UUID.randomUUID()
        val personName = "PersonName"
        given(
            personService.updateName(
                personId = eq(personId),
                newName = eq(personName)
            )
        ).willReturn(Person(id = personId, name = personName))

        //tested method invocation
        val updatedPerson = personFacade.updateNameWithDefaultValues(personId, personName)

        //assertions
        assertEquals(
            expected = Person(id = personId, name = personName),
            actual = updatedPerson
        )
    }

}