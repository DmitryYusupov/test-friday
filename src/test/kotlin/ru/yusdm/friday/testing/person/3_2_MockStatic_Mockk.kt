package ru.yusdm.friday.testing.person

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertTrue

class `3_2_MockStatic_Mockk` {
    private val personService: PersonService = PersonService()
    private val uuid = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
    private val localDate = LocalDate.of(1900, 11, 10)

    @AfterEach
    fun clearMockStatic() {
        unmockkStatic(UUID::class)
        unmockkStatic(LocalDate::class)
    }

    @Test
    fun `should return oldest person date of birth`() {
        //given
        mockkStatic(UUID::class)
        mockkStatic(LocalDate::class)
        every { UUID.randomUUID() } returns uuid
        every { LocalDate.now() } returns localDate

        //tested method invocation
        val actualPerson = personService.getOldestPerson()

        //assertions
        assertTrue(actualPerson.id == uuid && actualPerson.dateOfBirth == localDate)
    }


}