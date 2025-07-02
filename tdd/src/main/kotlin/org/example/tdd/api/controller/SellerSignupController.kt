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
        if (command.email.isBlank()) {
            return ResponseEntity.badRequest().build()
        }

        return ResponseEntity.noContent().build()
    }
}
