package ru.yusdm.friday.testing.banking.a_service

import org.springframework.stereotype.Service
import ru.yusdm.friday.testing.person.model.Person
import ru.yusdm.friday.testing.statistics.StatisticsCollector

@Service
class BankingFacade(
    private val bankingRepository: BankingRepository,
    private val statisticsCollector: StatisticsCollector
) {

    fun registerNewClient(person: Person): Person {
        statisticsCollector.incrementNewClientsCounter()
        println("Total new clients = ${statisticsCollector.getTotalNewClients()}")
        return bankingRepository.savePerson(person)
    }

}