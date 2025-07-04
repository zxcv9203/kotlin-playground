package org.example.tdd.api.controller

import org.example.tdd.UserPropertyValidator
import org.example.tdd.command.CreateShopperCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperSignupController {
    @PostMapping("/shopper/signup")
    fun signup(
        @RequestBody command: CreateShopperCommand,
    ): ResponseEntity<Unit> {
        if (!isCommandValid(command)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    private fun isCommandValid(command: CreateShopperCommand): Boolean = UserPropertyValidator.isEmailValid(command.email)
}
