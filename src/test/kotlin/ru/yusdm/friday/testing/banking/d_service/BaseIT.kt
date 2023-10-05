package ru.yusdm.friday.testing.banking.d_service

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.MockkBeans
import org.springframework.boot.test.context.SpringBootTest
import ru.yusdm.friday.testing.banking.a_service.BankingRepository
import ru.yusdm.friday.testing.banking.a_service.PoliceRepository

@SpringBootTest
@MockkBeans(MockkBean(BankingRepository::class, PoliceRepository::class))
abstract class BaseIT {
}