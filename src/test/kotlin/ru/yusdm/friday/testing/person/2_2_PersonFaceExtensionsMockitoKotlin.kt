package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.util.*
import kotlin.test.assertEquals

class `2_2_PersonFaceExtensionsMockitoKotlin` {
    private val personService: PersonService = mock()
    private val personFacade = PersonFacade(personService)

    @Test
    fun `shouldUpdate name with explicit params`() {
        val personId = UUID.randomUUID()
        val personName = "PersonName"

        given(
            personService.updateNameExtension(
                personId = personId,
                newName = personName,
                databaseContext = DatabaseContext(ctx = "dbContext"),
                securityContext = SecurityContext(ctx = "securityContext")
            )
        ).willReturn(Person(id = personId, name = personName))

        //tested method invocation
        val updatedPerson = personFacade.updateNameWithExtensionFunction(personId, personName)

        //assertions
        assertEquals(
            expected = Person(id = personId, name = personName),
            actual = updatedPerson
        )
    }
}