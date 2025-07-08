package org.example.tdd.api.controller

import org.example.tdd.view.SellerMeView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*

@RestController
class SellerMeController {
    @GetMapping("/seller/me")
    fun me(user: Principal): SellerMeView {
        val id = UUID.fromString(user.name)
        return SellerMeView(
            id = id,
            email = "",
            username = "",
        )
    }
}
