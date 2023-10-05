package ru.yusdm.friday.testing.banking.e_service

import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.yusdm.friday.testing.banking.a_service.BankingFacade
import ru.yusdm.friday.testing.banking.a_service.BankingRepository
import ru.yusdm.friday.testing.person.model.Person
import ru.yusdm.friday.testing.statistics.StatisticsCollector
import kotlin.test.assertEquals


class `1_BankingFacadeServiceTestIT`: BaseIT() {

    @Autowired
    private lateinit var bankingFacade: BankingFacade

    @Autowired
    private lateinit var statisticsCollector: StatisticsCollector

    @Autowired
    private lateinit var bankingRepository: BankingRepository

    @Test
    fun `should save new client`() {
        //given
        val person = Person("New Person")
        every { bankingRepository.savePerson(person) } returns Person(name = "New Person", metaInfo = "Some meta")

        //tested method invocation
        val actualSavePerson = bankingFacade.registerNewClient(person)

        //assertions
        assertEquals(expected = Person(name = "New Person", metaInfo = "Some meta"), actual = actualSavePerson)
        assertEquals(expected = 1, statisticsCollector.getTotalNewClients())
    }
}