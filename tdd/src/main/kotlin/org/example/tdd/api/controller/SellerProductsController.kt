package org.example.tdd.api.controller

import org.example.tdd.SellerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.UUID

@RestController
class SellerProductsController(
    private val sellerRepository: SellerRepository,
) {
    @PostMapping("/seller/products")
    fun registerProduct(user: Principal): ResponseEntity<Unit> {
        val id = UUID.fromString(user.name)
        sellerRepository.findById(id)
            ?: return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
