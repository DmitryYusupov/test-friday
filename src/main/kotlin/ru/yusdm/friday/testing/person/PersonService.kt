package ru.yusdm.friday.testing.person

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonService {

    @Autowired
    private lateinit var personRepository: PersonRepository

    fun registerUserAndSentConfirmationLetter(person: Person): Person {
        personRepository.save(person)
        return person
    }

    fun getAllRegisteredUsers(): List<Person> {
        return personRepository.findAll()
    }

}