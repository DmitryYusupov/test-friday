package ru.yusdm.friday.testing.person

import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.FieldPredicates
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

private val easyRandom = EasyRandom()

class `3_PersonEasyRandom` {

    @Test
    fun `should generate complex person with manual assignment values to field`() {
        //given
        val person = ComplexPerson(
            id = easyRandom.nextLong(),
            name = easyRandom.nextObject(String::class.java),
            pets = easyRandom.objects(ComplexPerson.Pet::class.java, 50).toList()
        )

        //assertions
        assertNotNull(person.id)
        assertTrue { person.name.isNotEmpty() }
        assertEquals(expected = 50, actual = person.pets.size)
    }

    @Test
    fun `should generate complex person`() {
        //given
        val person = easyRandom.nextObject(ComplexPerson::class.java)

        //assertions
        assertNotNull(person.id)
        assertTrue { person.name.isNotEmpty() }
        assertTrue { person.pets.isNotEmpty() }
    }

    @Test
    fun `should generate complex person with null id`() {
        //given
        val easyRandom = run {
            val parameters = EasyRandomParameters().excludeField {
                it.name.equals("id")
            }
            EasyRandom(parameters)
        }
        val person = easyRandom.nextObject(ComplexPerson::class.java)

        //assertions
        assertNull(person.id)
        assertTrue { person.name.isNotEmpty() }
        assertTrue { person.pets.isNotEmpty() }
    }

    @Test
    fun `should generate complex person with random params`() {
        //given
        val easyRandom = run {
            val parameters = EasyRandomParameters().apply {
                stringLengthRange(3, 3)
                collectionSizeRange(15, 15)
                excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(ComplexPerson::class.java)))
            }
            EasyRandom(parameters)
        }
        val person = easyRandom.nextObject(ComplexPerson::class.java)

        //assertions
        assertNull(person.id)
        assertTrue { person.name.isNotEmpty() }
        assertEquals(expected = 3, actual = person.name.length)
        assertEquals(expected = 15, actual = person.pets.size)
    }
}