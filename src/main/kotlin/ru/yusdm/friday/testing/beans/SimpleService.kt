package ru.yusdm.friday.testing.beans

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class SimpleService {

    @PostConstruct
    fun postConstruct() {
        println("AAAAA PostConstruct " + this.javaClass.simpleName + " " + this)
    }
}