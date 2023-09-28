package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class `3_3_OrderedTestWithLifecycle` {

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