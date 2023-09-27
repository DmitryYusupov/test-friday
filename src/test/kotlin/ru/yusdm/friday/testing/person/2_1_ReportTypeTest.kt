package ru.yusdm.friday.testing.person

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `2_1_ReportTypeTest` {

    @Test
    fun `PDF should return proper databaseCode`() {
        //given
        val pdfReportType = ReportType.PDF

        //tested method invocation
        val actualDatabaseCode = pdfReportType.databaseCode

        //assertions
        assertEquals(expected = "pdf", actual = actualDatabaseCode)
    }

    @Test
    fun `DOCX should return proper databaseCode`() {
        //given
        val pdfReportType = ReportType.DOCX

        //tested method invocation
        val actualDatabaseCode = pdfReportType.databaseCode

        //assertions
        assertEquals(expected = "docx", actual = actualDatabaseCode)
    }

}