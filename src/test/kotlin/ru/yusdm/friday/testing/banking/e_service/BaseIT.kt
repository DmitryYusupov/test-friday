package ru.yusdm.friday.testing.banking.e_service

import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import ru.yusdm.friday.testing.banking.a_service.BankingRepository
import ru.yusdm.friday.testing.banking.a_service.PoliceRepository

@SpringBootTest
@ContextConfiguration(classes = [BaseIT.MyTestConfiguration::class])
abstract class BaseIT {

    @TestConfiguration
    class MyTestConfiguration {

        @Bean
        fun policeRepository(): PoliceRepository {
            return mockk()
        }

        @Bean
        fun bankingRepository(): BankingRepository {
            return mockk()
        }

    }
}