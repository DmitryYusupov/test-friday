package ru.yusdm.friday.testing.repository

import org.junit.jupiter.api.BeforeAll
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@ActiveProfiles("test-postgresql")
abstract class BaseIT {

    companion object {

        private val postgres: PostgreSQLContainer<*> = PostgreSQLContainer(DockerImageName.parse("postgres:13.3"))
                .apply {
                    this.withDatabaseName("testDb").withUsername("root")
                            .withPassword("123456")
                }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }

        @JvmStatic
        @BeforeAll
        internal fun setUp() {
            postgres.start()
        }
    }

}