package ru.yusdm.friday.testing.person

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class `2_2_ReportTypeParametrizedTest` {

    @ParameterizedTest
    @MethodSource("getDatabaseCodeByReportTypeInputs")
    fun `should return proper databaseCode`(testInput: TestInput) {
        //given
        val pdfReportType = testInput.givenReportType

        //tested method invocation
        val actualDatabaseCode = pdfReportType.databaseCode

        //assertions
        assertEquals(expected = testInput.expectedDatabaseCode, actual = actualDatabaseCode)
    }

    companion object {

        data class TestInput(
            val givenReportType: ReportType,
            val expectedDatabaseCode: String
        )

        @JvmStatic
        fun getDatabaseCodeByReportTypeInputs(): Stream<TestInput> {
            return Stream.of(
                TestInput(ReportType.PDF, "pdf"),
                TestInput(ReportType.DOCX, "docx"),
            )
        }
    }
}

