package org.example.tdd.api.controller

import org.example.tdd.view.ShopperMeView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.UUID

@RestController
class ShopperMeController {
    @GetMapping("/shopper/me")
    fun me(principal: Principal): ShopperMeView {
        val id = UUID.fromString(principal.name)
        return ShopperMeView(
            id = id,
            email = "",
            username = "",
        )
    }
}
