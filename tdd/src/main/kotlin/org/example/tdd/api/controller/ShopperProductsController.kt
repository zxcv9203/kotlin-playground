package org.example.tdd.api.controller

import jakarta.persistence.EntityManager
import org.example.tdd.ProductRepository
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.example.tdd.view.SellerView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperProductsController(
    private val productRepository: ProductRepository,
    private val entityManager: EntityManager,
) {
    @GetMapping("/shopper/products")
    fun getProducts(): PageCarrier<ProductView> {
        val queryString =
            """
            SELECT new org.example.tdd.api.controller.ProductSellerTuple(p, s)
            FROM Product p 
            JOIN Seller s ON p.sellerId = s.id
            ORDER BY p.dataKey DESC
            """.trimIndent()

        return entityManager
            .createQuery(queryString, ProductSellerTuple::class.java)
            .resultList
            .map { tuple ->
                ProductView(
                    id = tuple.product.id,
                    name = tuple.product.name,
                    description = tuple.product.description,
                    imageUri = tuple.product.imageUri,
                    priceAmount = tuple.product.priceAmount,
                    stockQuantity = tuple.product.stockQuantity,
                    seller =
                        SellerView(
                            id = tuple.seller.id,
                            username = tuple.seller.username,
                        ),
                )
            }.let {
                PageCarrier(it.toTypedArray(), "")
            }
    }
}
