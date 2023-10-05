package ru.yusdm.friday.testing.banking.a_service

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.yusdm.friday.testing.person.model.Person
import ru.yusdm.friday.testing.statistics.StatisticsCollector
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
class `2_PoliceServiceTestIT` {

    @Autowired
    private lateinit var policeService: PoliceService

    @Autowired
    private lateinit var statisticsCollector: StatisticsCollector

    @MockkBean
    private lateinit var policeRepository: PoliceRepository

    @Test
    fun `should return person has no law issues`() {
        //given
        val person = Person("New Person")
        every { policeRepository.findByPersonNameAndHasLawIssueEqTrue(person.name) } returns null

        //tested method invocation
        val noLawIssues = policeService.verifyPersonNoLawIssues(person)

        //assertions
        assertTrue(noLawIssues)
        assertEquals(expected = 1, actual = statisticsCollector.getTotalNumberOfPoliceServiceInvocations())
    }
}