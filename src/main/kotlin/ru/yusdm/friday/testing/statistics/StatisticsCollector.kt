package ru.yusdm.friday.testing.statistics

import io.prometheus.client.Counter
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class StatisticsCollector {

    private lateinit var newClientsCounter: Counter

    private lateinit var policeServiceIntegrationNumber: Counter

    @PostConstruct
    fun registerMetrics() {
        newClientsCounter = Metric.NEW_CLIENTS.toCounter()
        policeServiceIntegrationNumber = Metric.POLICE_INTEGRATIONS.toCounter()
    }

    fun incrementNewClientsCounter() {
        newClientsCounter.inc()
    }

    fun incrementPoliceServiceInvocation() {
        policeServiceIntegrationNumber.inc()
    }

    fun getTotalNumberOfPoliceServiceInvocations(): Long {
        return policeServiceIntegrationNumber.get().toLong()
    }

    fun getTotalNewClients(): Long {
        return newClientsCounter.get().toLong()
    }

    private enum class Metric(
        val metricName: String,
        val labelNames: Array<String>,
        val help: String
    ) {
        NEW_CLIENTS(
            metricName = "NewClients",
            labelNames = arrayOf(),
            help = "Number of new clients"
        ),
        POLICE_INTEGRATIONS(
            metricName = "PoliceIntegrations",
            labelNames = arrayOf(),
            help = "Number of integrations with police"
        );

        fun toCounter(): Counter = Counter.build()
            .name(metricName)
            .labelNames(*labelNames)
            .help(help)
            .register()
    }
}