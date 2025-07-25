package org.example.tdd.api.controller

import org.example.tdd.Product
import org.example.tdd.ProductRepository
import org.example.tdd.command.RegisterProductCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
class SellerProductsController(
    private val productRepository: ProductRepository,
) {
    @PostMapping("/seller/products")
    fun registerProduct(
        @RequestBody command: RegisterProductCommand,
    ): ResponseEntity<Unit> {
        if (!isValidUri(command.imageUri)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        val product =
            Product(
                id = UUID.randomUUID(),
            )
        productRepository.save(product)

        val location = URI.create("/seller/products/${UUID.randomUUID()}")
        return ResponseEntity
            .created(location)
            .build()
    }

    private fun isValidUri(imageUri: String): Boolean {
        try {
            val uri = URI.create(imageUri)
            return uri.host != null
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    @GetMapping("/seller/products/{id}")
    fun findProducts(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> =
        productRepository
            .findById(id)
            ?.let { ResponseEntity.ok().build() }
            ?: ResponseEntity.notFound().build()
}
