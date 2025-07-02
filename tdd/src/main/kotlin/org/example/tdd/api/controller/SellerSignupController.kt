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
        return if (command.email.isBlank()) {
            ResponseEntity.badRequest().build()
        } else if (!command.email.matches(emailRegex)) {
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
