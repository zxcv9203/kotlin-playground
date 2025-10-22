package org.example.tdd

object TestDataSource {
    @JvmStatic
    fun invalidPasswords(): List<String> =
        listOf(
            "",
            "pass",
            "pass123",
            "1234password",
            "password1234",
            "pass5678word",
        )
}
