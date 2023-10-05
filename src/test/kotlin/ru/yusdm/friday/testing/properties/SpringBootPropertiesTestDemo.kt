package ru.yusdm.friday.testing.properties

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertTrue

@SpringBootTest(properties = ["feature.a=value", "feature.b=value"])
class SpringBootPropertiesTestDemoA {

    @Test
    fun `should be true`() {
        assertTrue { true }
    }

}

@SpringBootTest(properties = ["feature.a=value", "feature.b=value"])
class SpringBootPropertiesTestDemoB {

    @Test
    fun `should be true`() {
        assertTrue { true }
    }

}