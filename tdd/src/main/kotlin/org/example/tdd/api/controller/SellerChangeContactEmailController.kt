package org.example.tdd.api.controller

import org.example.tdd.SellerRepository
import org.example.tdd.UserPropertyValidator.isEmailValid
import org.example.tdd.command.ChangeContactEmailCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.UUID

@RestController
class SellerChangeContactEmailController(
    val sellerRepository: SellerRepository,
) {
    @PostMapping("/seller/changeContactEmail")
    fun changeContactEmail(
        @RequestBody command: ChangeContactEmailCommand,
        user: Principal,
    ): ResponseEntity<Unit> {
        if (!isEmailValid(command.contactEmail)) {
            return ResponseEntity.badRequest().build()
        }
        val id = UUID.fromString(user.name)
        val seller =
            sellerRepository.findById(id)
                ?: throw IllegalStateException("Seller not found: $id")
        seller.changeContactEmail(command.contactEmail)
        sellerRepository.save(seller)
        return ResponseEntity.noContent().build()
    }
}
