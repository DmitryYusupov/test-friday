package ru.yusdm.friday.testing.banking.a_service

import org.springframework.stereotype.Repository
import ru.yusdm.friday.testing.person.model.Person

@Repository
class BankingRepository {

    fun savePerson(person: Person): Person {
        return person
    }

}