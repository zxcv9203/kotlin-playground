package org.example.tdd

private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
private const val USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,}$"

object UserPropertyValidator {
    fun isEmailValid(email: String?): Boolean = email != null && email.matches(Regex(EMAIL_REGEX))

    fun isUsernameValid(username: String?): Boolean = username != null && username.matches(Regex(USERNAME_REGEX))

    fun isPasswordValid(password: String?): Boolean = password != null && password.length >= 8
}
