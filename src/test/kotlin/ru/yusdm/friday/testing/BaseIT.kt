package ru.yusdm.friday.testing

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.support.TransactionTemplate

@SpringBootTest
@ActiveProfiles("test")
class BaseIT {

    @Autowired
    protected lateinit var entityManager: EntityManager

    @Autowired
    protected lateinit var transactionTemplate: TransactionTemplate

    @Autowired
    protected lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    protected fun clearDatabase(){
        transactionTemplate.execute {
            namedParameterJdbcTemplate.update("DELETE FROM city", MapSqlParameterSource())
            namedParameterJdbcTemplate.update("DELETE FROM country", MapSqlParameterSource())
        }
    }
}