package org.example.tdd

private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

object UserPropertyValidator {
    fun isEmailValid(email: String?): Boolean = email != null && email.matches(Regex(EMAIL_REGEX))
}
