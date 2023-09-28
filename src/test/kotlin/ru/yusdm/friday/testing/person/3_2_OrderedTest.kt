package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
/**
 * New instance of 3_2_OrderedTest is created.
 */
class `3_2_OrderedTest` {

    var counter = 0

    @Test
    @Order(1)
    fun aAssertCounterAndInc() {
        assertEquals(0, counter)
        counter++
    }

    @Test
    @Order(2)
    fun bAssertCounterAndInc() {
        assertEquals(1, counter)
        counter++
    }

    @Test
    @Order(3)
    fun cAssertCounterAndInc() {
        assertEquals(2, counter)
        counter++
    }
}