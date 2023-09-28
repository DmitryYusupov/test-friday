package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.mockito.MockedStatic
import org.mockito.Mockito
import java.time.LocalDate
import java.util.UUID
import kotlin.test.assertTrue

class `3_1_MockStatic_Mockito` {
    private val personService: PersonService = PersonService()

    private val uuid = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
    private val localDate = LocalDate.of(1900, 11, 10)

    @Test
    fun `should return oldest person date of birth`() {
        //given
        var localDateStaticStub: MockedStatic<LocalDate>? = null
        var uuidStaticStub: MockedStatic<UUID>? = null

        try {
            localDateStaticStub = Mockito.mockStatic(LocalDate::class.java)
            uuidStaticStub = Mockito.mockStatic(UUID::class.java)

            uuidStaticStub!!.`when`<UUID> { UUID.randomUUID() }.thenReturn(uuid)
            localDateStaticStub!!.`when`<LocalDate> { LocalDate.now() }.thenReturn(localDate)

            //tested method invocation
            val actualPerson = personService.getOldestPerson()

            //assertions
            assertTrue(actualPerson.id == uuid && actualPerson.dateOfBirth == localDate)
        } finally {
            localDateStaticStub?.close()
            uuidStaticStub?.close()
        }
    }


}