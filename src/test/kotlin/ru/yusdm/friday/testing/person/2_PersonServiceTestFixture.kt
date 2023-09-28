package ru.yusdm.friday.testing.person

import com.appmattus.kotlinfixture.decorator.nullability.AlwaysNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.decorator.optional.AlwaysOptionalStrategy
import com.appmattus.kotlinfixture.decorator.optional.optionalStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private val fixture = kotlinFixture {
    nullabilityStrategy(AlwaysNullStrategy)
    optionalStrategy(AlwaysOptionalStrategy)
}

class `2_PersonServiceTestFixture` {

    private val personService = PersonService()

    @Test
    fun `should save and assign id`() {
        //given
        val person = fixture<Person>().copy(firstName = "AnyName")

        //tested method invocation
        val savePerson = personService.save(person)

        //assertions
        assertTrue { savePerson.id != null }
    }

    @Test
    fun `should update name`() {
        //given
        val person = fixture<Person>()

        //tested method invocation
        val updatedPerson = personService.updateFirstName(person)

        //assertions
        assertEquals(expected = "UpdatedFirstName", actual = updatedPerson.firstName)
    }

    @Test
    fun `should update last name`() {
        //given
        val person = fixture<Person>()

        //tested method invocation
        val updatedPerson = personService.updateLastName(person)

        //assertions
        assertEquals(expected = "UpdatedLastName", actual = updatedPerson.lastName)

    }

    @Test
    fun `should update age`() {
        //given
        val person = fixture<Person>()

        //tested method invocation
        val updatedPerson = personService.updateAge(person)

        //assertions
        assertEquals(expected = 1000, actual = updatedPerson.age)
    }

    @Test
    fun `should change pet name`() {
        val pet = fixture<Pet>() {
            property(Pet::name) { "Barsik" }
        }

        assertTrue { pet.name == "Barsik" }
    }

}