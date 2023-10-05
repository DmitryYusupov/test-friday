package ru.yusdm.friday.testing.banking.a_service

import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import com.ninjasquad.springmockk.MockkBean
import ru.yusdm.friday.testing.person.model.Person
import ru.yusdm.friday.testing.statistics.StatisticsCollector
import kotlin.test.assertEquals


@SpringBootTest
class `1_BankingFacadeServiceTestIT` {

    @Autowired
    private lateinit var bankingFacade: BankingFacade

    @Autowired
    private lateinit var statisticsCollector: StatisticsCollector

    @MockkBean
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