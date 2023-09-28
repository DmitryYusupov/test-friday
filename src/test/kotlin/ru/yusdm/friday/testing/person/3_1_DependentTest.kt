package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `3_1_DependentTest` {

    var counter = 0

    @Test
    fun aAssertCounterAndInc() {
        assertEquals(0, counter)
        counter++
    }

    @Test
    fun bAssertCounterAndInc() {
        assertEquals(1, counter)
        counter++
    }

    @Test
    fun cAssertCounterAndInc() {
        assertEquals(2, counter)
        counter++

    }
}