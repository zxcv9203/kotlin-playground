package org.example.tdd.api.controller

import org.example.tdd.Shopper
import org.example.tdd.ShopperRepository
import org.example.tdd.UserPropertyValidator
import org.example.tdd.command.CreateShopperCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ShopperSignupController(
    private val shopperRepository: ShopperRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping("/shopper/signup")
    fun signup(
        @RequestBody command: CreateShopperCommand,
    ): ResponseEntity<Unit> {
        if (!isCommandValid(command)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        val shopper =
            Shopper(
                email = command.email!!,
                username = command.username!!,
                hashedPassword = passwordEncoder.encode(command.password),
                id = UUID.randomUUID(),
            )
        shopperRepository.save(shopper)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    private fun isCommandValid(command: CreateShopperCommand): Boolean =
        UserPropertyValidator.isEmailValid(command.email) &&
            UserPropertyValidator.isUsernameValid(command.username) &&
            UserPropertyValidator.isPasswordValid(command.password)
}
