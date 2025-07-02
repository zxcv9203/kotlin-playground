package org.example.tdd.api.controller

import org.example.tdd.command.CreateSellerCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SellerSignupController {
    @PostMapping("/seller/signup")
    fun signup(
        @RequestBody command: CreateSellerCommand,
    ): ResponseEntity<Unit> {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
        val usernameRegex = Regex("^[a-zA-Z0-9_-]*$")
        return if (!command.email.matches(emailRegex)) {
            ResponseEntity.badRequest().build()
        } else if (command.username.isBlank()) {
            ResponseEntity.badRequest().build()
        } else if (command.username.length < 3) {
            ResponseEntity.badRequest().build()
        } else if (!command.username.matches(usernameRegex)) {
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
