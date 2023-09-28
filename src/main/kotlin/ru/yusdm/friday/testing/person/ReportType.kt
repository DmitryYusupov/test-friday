package ru.yusdm.friday.testing.person

enum class ReportType(val databaseCode: String, val clientCode: String) {
    PDF("pdf", "pdfClient"),
    DOCX("docx", "docXClient")
}