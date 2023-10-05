package ru.yusdm.friday.testing.banking.a_service

import org.springframework.stereotype.Repository
import ru.yusdm.friday.testing.person.model.Person

@Repository
class PoliceRepository {

    fun findByPersonNameAndHasLawIssueEqTrue(personName: String): Person? {
        return Person("Fake person")
    }
}