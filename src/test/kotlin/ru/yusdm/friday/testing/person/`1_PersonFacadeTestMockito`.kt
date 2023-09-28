package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class `1_PersonFacadeTestMockito` {

    private val personService: PersonService = mock()
    private val personFacade = PersonFacade(personService)

    @Test
    fun `shouldUpdate name`() {

    }
}