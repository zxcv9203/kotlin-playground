package org.example.tdd.api.controller

import org.example.tdd.view.ShopperMeView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ShopperMeController {
    @GetMapping("/shopper/me")
    fun me(): ShopperMeView =
        ShopperMeView(
            id = UUID.randomUUID(),
            email = "",
            username = "",
        )
}
