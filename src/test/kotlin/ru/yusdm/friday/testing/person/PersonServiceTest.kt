package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [PersonServiceTestConfiguration::class])
class PersonServiceTest {

    @Autowired
    private lateinit var personService: PersonService

    @Test
    fun `should save person, and send confirmation letter`() {
        val person = Person(id = 1, name = "Name_1")

        personService.registerUserAndSentConfirmationLetter(person)

        val savedPerson = personService.getAllRegisteredUsers().firstOrNull { it.id == 1L }
        assertEquals(expected = person, actual = savedPerson)
    }

    @Test
    fun `should throw duplicate key exception while register multiple users`() {
        assertThrows<IllegalStateException> {
            val person1 = Person(id = 2, name = "Name_1")
            val person2 = Person(id = 2, name = "Name_2")

            personService.registerUserAndSentConfirmationLetter(person1)
            personService.registerUserAndSentConfirmationLetter(person2)
        }
    }
}

/**
 * Apply TestConfiguration to make it work with SpringBootTest
 */
@Configuration
class PersonServiceTestConfiguration {

    @Bean
    fun personService(): PersonService {
        return PersonService()
    }

    @Bean
    fun personRepository(): PersonRepository {

        return object : PersonRepository {
            private val idsStorage = mutableSetOf<Long>()
            private val personRepositoryDefaultImpl = PersonRepositoryDefaultImpl()

            override fun save(person: Person): Person {
                if (!idsStorage.add(person.id)) {
                    throw IllegalStateException("Duplicated key id = ${person.id}")
                }
                return personRepositoryDefaultImpl.save(person)
            }

            override fun getByIdOrNull(id: Long): Person? {
                return personRepositoryDefaultImpl.getByIdOrNull(id)
            }

            override fun findAll(): List<Person> {
                return personRepositoryDefaultImpl.findAll()
            }
        }
    }

}