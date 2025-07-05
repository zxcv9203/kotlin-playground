package org.example.tdd

object TestDataSource {
    @JvmStatic
    fun invalidPasswords(): List<String> =
        listOf(
            "",
            "pass",
            "1234567",
        )
}
