package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class `1_PersonServiceTest` {

    private val personService = PersonService()

    @Test
    fun `should save person and assign id`() {
        //given
        val person = Person(name = "Test person", birthDate = LocalDate.now())

        //tested method invocation
        val actual = personService.savePerson(person)

        //assertions
        assertEquals(actual = actual.id, expected = 1L)
    }
}