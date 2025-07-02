package org.example.tdd.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SellerSignupController {
    @PostMapping("/seller/signup")
    fun signup(): ResponseEntity<Unit> = ResponseEntity.noContent().build()
}
