package org.example.tdd.api.controller

import org.example.tdd.SellerRepository
import org.example.tdd.view.SellerMeView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.UUID

@RestController
class SellerMeController(
    private val sellerRepository: SellerRepository,
) {
    @GetMapping("/seller/me")
    fun me(user: Principal): SellerMeView {
        val id = UUID.fromString(user.name)
        val seller =
            sellerRepository.findById(id)
                ?: throw IllegalArgumentException("Seller not found with id: $id")
        return SellerMeView(
            id = id,
            email = seller.email,
            username = seller.username,
            contactEmail = seller.contactEmail,
        )
    }
}
