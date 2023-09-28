package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals


class `4_1_NestedTests` {

    @Nested
    inner class ReportTypeDatabaseCodeTest {

        @ParameterizedTest
        @MethodSource("ru.yusdm.friday.testing.person.4_1_NestedTests#getDatabaseCodeByReportTypeInputs")
        fun `should return proper databaseCode`(testInput: TestInput) {
            //given
            val pdfReportType = testInput.givenReportType

            //tested method invocation
            val actualDatabaseCode = pdfReportType.databaseCode

            //assertions
            assertEquals(expected = testInput.expectedDatabaseCode, actual = actualDatabaseCode)
        }
    }

    @Nested
    inner class ReportTypeClientCodeTest {

        @ParameterizedTest
        @MethodSource("ru.yusdm.friday.testing.person.4_1_NestedTests#getClientCodeByReportTypeInputs")
        fun `should return proper clientCode`(testInput: TestInput) {
            //given
            val pdfReportType = testInput.givenReportType

            //tested method invocation
            val actualClientCode = pdfReportType.clientCode

            //assertions
            assertEquals(expected = testInput.expectedClientCode, actual = actualClientCode)
        }

    }

    companion object {

        data class TestInput(
            val givenReportType: ReportType,
            val expectedDatabaseCode: String = "",
            val expectedClientCode: String = ""
        )

        @JvmStatic
        fun getDatabaseCodeByReportTypeInputs(): Stream<TestInput> {
            return Stream.of(
                TestInput(ReportType.PDF, expectedDatabaseCode = "pdf"),
                TestInput(ReportType.DOCX, expectedDatabaseCode = "docx"),
            )
        }

        @JvmStatic
        fun getClientCodeByReportTypeInputs(): Stream<TestInput> {
            return Stream.of(
                TestInput(ReportType.PDF, expectedClientCode = "pdfClient"),
                TestInput(ReportType.DOCX, expectedClientCode = "docXClient"),
            )
        }
    }
}