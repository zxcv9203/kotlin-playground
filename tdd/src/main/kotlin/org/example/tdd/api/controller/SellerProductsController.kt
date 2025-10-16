package org.example.tdd.api.controller

import org.example.tdd.Product
import org.example.tdd.ProductRepository
import org.example.tdd.command.RegisterProductCommand
import org.example.tdd.commandmodel.RegisterProductCommandExecutor
import org.example.tdd.view.ArrayCarrier
import org.example.tdd.view.SellerProductView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.security.Principal
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
        val id = UUID.randomUUID()
        val sellerId = UUID.fromString(user.name)
        val executor = RegisterProductCommandExecutor(productRepository::save)
        executor.execute(id, sellerId, command)

        val location = URI.create("/seller/products/$id")
        return ResponseEntity
            .created(location)
            .build()
    }

    @GetMapping("/seller/products/{id}")
    fun findProducts(
        @PathVariable id: UUID,
        user: Principal,
    ): ResponseEntity<SellerProductView> =
        productRepository
            .findById(id)
            ?.takeIf { it.sellerId == UUID.fromString(user.name) }
            ?.let { convertToView(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/seller/products")
    fun findProductsById(user: Principal): ResponseEntity<ArrayCarrier<SellerProductView>> {
        val sellerId = UUID.fromString(user.name)
        val response =
            productRepository
                .findBySellerId(sellerId)
                .sortedByDescending { it.registeredTimeUtc }
                .map { convertToView(it) }
                .toTypedArray()
        return ResponseEntity.ok(ArrayCarrier(response))
    }

    private fun convertToView(product: Product): SellerProductView =
        SellerProductView(
            id = product.id,
            name = product.name,
            description = product.description,
            priceAmount = product.priceAmount,
            imageUri = product.imageUri,
            stockQuantity = product.stockQuantity,
            registeredTimeUtc = product.registeredTimeUtc,
        )
}
