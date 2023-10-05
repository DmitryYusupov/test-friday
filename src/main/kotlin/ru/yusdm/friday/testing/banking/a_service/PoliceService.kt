package ru.yusdm.friday.testing.banking.a_service

import org.springframework.stereotype.Service
import ru.yusdm.friday.testing.person.model.Person
import ru.yusdm.friday.testing.statistics.StatisticsCollector

@Service
class PoliceService(
    private val policeRepository: PoliceRepository,
    private val statisticsCollector: StatisticsCollector
) {

    fun verifyPersonNoLawIssues(person: Person): Boolean {
        statisticsCollector.incrementPoliceServiceInvocation()
        val personWithLawIssues = policeRepository.findByPersonNameAndHasLawIssueEqTrue(person.name)

        return personWithLawIssues == null
    }

}