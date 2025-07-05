package org.example.tdd.api.controller

import org.example.tdd.Seller
import org.example.tdd.SellerRepository
import org.example.tdd.UserPropertyValidator
import org.example.tdd.command.CreateSellerCommand
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
        UserPropertyValidator.isEmailValid(command.email) &&
            UserPropertyValidator.isUsernameValid(command.username) &&
            UserPropertyValidator.isPasswordValid(command.password)
}
