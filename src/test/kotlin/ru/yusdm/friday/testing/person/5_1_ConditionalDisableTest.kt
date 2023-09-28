package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import kotlin.jvm.optionals.getOrNull
import kotlin.test.assertTrue

class `5_1_ConditionalDisableTest` {

    @Test
    @DisableForServers(serverNamePrefix = "aws-us1-")
    fun test_1() {
        assertTrue(true)
    }

    @Test
    @DisableForServers(serverNamePrefix = "aws-fr1-")
    fun test_2() {
        assertTrue(true)
    }

    @Test
    fun test_3() {
        assertTrue(true)
    }
}

class DisableTestForServersCondition : ExecutionCondition {

    override fun evaluateExecutionCondition(context: ExtensionContext): ConditionEvaluationResult {
        val disableForServersAnnotation = context.element.getOrNull()?.let {
            val result: DisableForServers? = it.annotations.firstOrNull { annotation ->
                annotation.annotationClass == DisableForServers::class
            } as? DisableForServers
            result
        }


        return if (disableForServersAnnotation != null) {
            val shouldSkip = getServerName().startsWith(disableForServersAnnotation.serverNamePrefix)

            if (shouldSkip) {
                SKIP
            } else {
                GO
            }
        } else {
            GO
        }
    }

    private fun getServerName(): String {
        return "aws-us1-any"
    }


    companion object {
        private val GO: ConditionEvaluationResult =
            ConditionEvaluationResult.enabled("No annotation or servername suits tests condition")
        private val SKIP: ConditionEvaluationResult =
            ConditionEvaluationResult.disabled("Should not run on this server due to company politics")
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(DisableTestForServersCondition::class)
annotation class DisableForServers(val serverNamePrefix: String)