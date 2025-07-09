package org.example.tdd.api.controller

import org.example.tdd.ShopperRepository
import org.example.tdd.view.ShopperMeView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.UUID

@RestController
class ShopperMeController(
    private val shopperRepository: ShopperRepository,
) {
    @GetMapping("/shopper/me")
    fun me(principal: Principal): ShopperMeView {
        val id = UUID.fromString(principal.name)
        val shopper =
            shopperRepository.findById(id)
                ?: throw IllegalArgumentException("Shopper not found with id: $id")
        return ShopperMeView(
            id = id,
            email = shopper.email,
            username = shopper.username,
        )
    }
}
