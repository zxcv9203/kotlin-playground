package org.example.tdd.api.controller

import org.example.tdd.Seller
import org.example.tdd.SellerRepository
import org.example.tdd.command.CreateSellerCommand
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
private const val USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,}$"

@RestController
class SellerSignupController(
    private val sellerRepository: SellerRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping("/seller/signup")
    fun signup(
        @RequestBody command: CreateSellerCommand,
    ): ResponseEntity<Unit> {
        if (!isCommandValid(command)) {
            return ResponseEntity.badRequest().build()
        }
        val seller =
            Seller(
                email = command.email!!,
                username = command.username!!,
                hashedPassword = passwordEncoder.encode(command.password),
            )
        try {
            sellerRepository.save(seller)
        } catch (e: DataIntegrityViolationException) {
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity.noContent().build()
    }

    private fun isCommandValid(command: CreateSellerCommand): Boolean =
        isEmailValid(command.email) &&
            isUsernameValid(command.username) &&
            isPasswordValid(command.password)

    private fun isPasswordValid(password: String?): Boolean = password != null && password.length >= 8

    private fun isUsernameValid(username: String?): Boolean = username != null && username.matches(Regex(USERNAME_REGEX))

    private fun isEmailValid(email: String?): Boolean = email != null && email.matches(Regex(EMAIL_REGEX))
}
