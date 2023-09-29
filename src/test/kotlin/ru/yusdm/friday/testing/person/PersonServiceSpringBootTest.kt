package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class PersonServiceSpringBootTest {

    @Autowired
    private lateinit var personService: PersonService

    @Test
    fun `should save person, and send confirmation letter`() {
        val person = Person(id = 1, name = "Name_1")

        personService.registerUserAndSentConfirmationLetter(person)

        val savedPerson = personService.getAllRegisteredUsers().firstOrNull { it.id == 1L }
        assertEquals(expected = person, actual = savedPerson)
    }

}