package org.example.tdd.api.controller

import org.example.tdd.ProductRepository
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.example.tdd.view.SellerView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ShopperProductsController(
    private val productRepository: ProductRepository,
) {
    @GetMapping("/shopper/products")
    fun getProducts(): PageCarrier<ProductView> =
        productRepository
            .findAll()
            .map {
                ProductView(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    imageUri = it.imageUri,
                    priceAmount = it.priceAmount,
                    stockQuantity = it.stockQuantity,
                    seller =
                        SellerView(
                            id = UUID.randomUUID(),
                            username = "",
                        ),
                )
            }.let { PageCarrier(it.toTypedArray(), "") }
}
