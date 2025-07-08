package org.example.tdd.api.controller

import org.example.tdd.view.SellerMeView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class SellerMeController {
    @GetMapping("/seller/me")
    fun me(): SellerMeView =
        SellerMeView(
            id = UUID.randomUUID(),
            email = "",
            username = "",
        )
}
