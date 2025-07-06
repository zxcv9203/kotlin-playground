package org.example.tdd.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SellerMeController {
    @GetMapping("/seller/me")
    fun me() {
    }
}
