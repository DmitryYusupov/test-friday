package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.*
import kotlin.test.assertEquals

class `2_1_PersonFaceExtensionsMockito` {

    private val personService: PersonService = mock(PersonService::class.java)
    private val personFacade: PersonFacade = PersonFacade(personService)

    @Test
    fun `shouldUpdate name with explicit params`() {
        val personId = UUID.randomUUID()
        val personName = "PersonName"
        `when`(
            personService.updateNameExtension(
                personId = personId,
                newName = personName,
                databaseContext = DatabaseContext(ctx = "dbContext"),
                securityContext = SecurityContext(ctx = "securityContext")
            )
        ).thenReturn(Person(personId, personName))

        //tested method invocation
        val updatedPerson = personFacade.updateNameWithExtensionFunction(personId, personName)

        //assertions
        assertEquals(
            expected = Person(id = personId, name = personName),
            actual = updatedPerson
        )
    }
}