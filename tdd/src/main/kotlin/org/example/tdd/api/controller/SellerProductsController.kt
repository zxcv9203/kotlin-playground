package org.example.tdd.api.controller

import org.example.tdd.Product
import org.example.tdd.ProductRepository
import org.example.tdd.command.RegisterProductCommand
import org.example.tdd.view.SellerProductView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.security.Principal
import java.time.LocalDateTime
import java.util.UUID

@RestController
class SellerProductsController(
    private val productRepository: ProductRepository,
) {
    @PostMapping("/seller/products")
    fun registerProduct(
        @RequestBody command: RegisterProductCommand,
        user: Principal,
    ): ResponseEntity<Unit> {
        if (!isValidUri(command.imageUri)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        val id = UUID.randomUUID()
        val product =
            Product(
                id = id,
                sellerId = UUID.fromString(user.name),
                name = command.name,
                description = command.description,
                priceAmount = command.priceAmount,
                imageUri = command.imageUri,
                stockQuantity = command.stockQuantity,
            )
        productRepository.save(product)

        val location = URI.create("/seller/products/$id")
        return ResponseEntity
            .created(location)
            .build()
    }

    private fun isValidUri(imageUri: String): Boolean {
        try {
            val uri = URI.create(imageUri)
            return uri.host != null
        } catch (_: IllegalArgumentException) {
            return false
        }
    }

    @GetMapping("/seller/products/{id}")
    fun findProducts(
        @PathVariable id: UUID,
        user: Principal,
    ): ResponseEntity<SellerProductView> =
        productRepository
            .findById(id)
            ?.takeIf { it.sellerId == UUID.fromString(user.name) }
            ?.let {
                SellerProductView(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    priceAmount = it.priceAmount,
                    imageUri = it.imageUri,
                    stockQuantity = it.stockQuantity,
                    registeredTimeUtc = LocalDateTime.now(),
                )
            }?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
}
