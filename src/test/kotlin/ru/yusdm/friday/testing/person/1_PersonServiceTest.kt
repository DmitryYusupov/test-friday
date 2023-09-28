package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class `1_PersonServiceTest` {

    private val personService = PersonService()

    @Test
    fun `should save and assign id`() {
        //given
        val person = Person(
            id = null,
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            age = 10,
            birthDate = LocalDate.now()
        )

        //tested method invocation
        val savePerson = personService.save(person)

        //assertions
        assertTrue { savePerson.id != null }
    }

    @Test
    fun `should update name`() {
        //given
        val person = Person(
            id = 1,
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            age = 10,
            birthDate = LocalDate.now()
        )

        //tested method invocation
        val updatedPerson = personService.updateFirstName(person)

        //assertions
        assertEquals(expected = "UpdatedFirstName", actual = updatedPerson.firstName)
    }

    @Test
    fun `should update last name`() {
        //given
        val person = Person(
            id = 1,
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            age = 10,
            birthDate = LocalDate.now()
        )

        //tested method invocation
        val updatedPerson = personService.updateLastName(person)

        //assertions
        assertEquals(expected = "UpdatedLastName", actual = updatedPerson.lastName)

    }

    @Test
    fun `should update age`() {
        //given
        val person = Person(
            id = 1,
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            age = 10,
            birthDate = LocalDate.now()
        )

        //tested method invocation
        val updatedPerson = personService.updateAge(person)

        //assertions
        assertEquals(expected = 1000, actual = updatedPerson.age)
    }

}