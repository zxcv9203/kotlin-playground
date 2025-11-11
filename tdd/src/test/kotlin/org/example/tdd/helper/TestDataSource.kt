package org.example.tdd.helper

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

    @JvmStatic
    fun invalidEmails() =
        listOf(
            "",
            "invalid-contact-email",
            "contact-email@",
            "contact-email@test",
            "contact@test.",
            "contact@.com",
        )
}
