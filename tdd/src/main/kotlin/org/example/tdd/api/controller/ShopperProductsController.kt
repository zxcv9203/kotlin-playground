package org.example.tdd.api.controller

import jakarta.persistence.EntityManager
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.example.tdd.view.SellerView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Base64
import kotlin.text.Charsets.UTF_8

@RestController
class ShopperProductsController(
    private val entityManager: EntityManager,
) {
    @GetMapping("/shopper/products")
    fun getProducts(
        @RequestParam(required = false) continuationToken: String?,
    ): PageCarrier<ProductView> {
        val queryString =
            """
            SELECT new org.example.tdd.api.controller.ProductSellerTuple(p, s)
            FROM Product p 
            JOIN Seller s ON p.sellerId = s.id
            WHERE :cursor IS NULL OR p.dataKey <= :cursor 
            ORDER BY p.dataKey DESC
            """.trimIndent()

        val pageSize = 10
        val results =
            entityManager
                .createQuery(queryString, ProductSellerTuple::class.java)
                .setParameter("cursor", decodeCursor(continuationToken))
                .setMaxResults(pageSize + 1)
                .resultList

        val items =
            results
                .take(10)
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
                }.toTypedArray()

        val next = results.last().product.dataKey
        return items
            .let { PageCarrier(it, encodeCursor(next)) }
    }

    private fun decodeCursor(continuationToken: String?): Long? {
        if (continuationToken.isNullOrEmpty()) {
            return null
        }
        return continuationToken
            .let { Base64.getDecoder().decode(it) }
            .toString(UTF_8)
            .toLong()
    }

    private fun encodeCursor(cursor: Long): String =
        cursor
            .toString()
            .toByteArray(UTF_8)
            .let { Base64.getEncoder().encodeToString(it) }
}
