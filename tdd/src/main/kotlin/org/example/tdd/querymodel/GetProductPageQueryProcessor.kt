package org.example.tdd.querymodel

import jakarta.persistence.EntityManager
import org.example.tdd.query.GetProductPage
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.example.tdd.view.SellerView
import java.util.Base64
import kotlin.text.Charsets.UTF_8

class GetProductPageQueryProcessor(
    val entityManager: EntityManager,
) {
    fun process(query: GetProductPage): PageCarrier<ProductView> {
        val queryString =
            """
            SELECT new org.example.tdd.querymodel.ProductSellerTuple(p, s)
            FROM Product p 
            JOIN Seller s ON p.sellerId = s.id
            WHERE :cursor IS NULL OR p.dataKey <= :cursor 
            ORDER BY p.dataKey DESC
            """.trimIndent()

        val pageSize = 10
        val results =
            entityManager
                .createQuery(queryString, ProductSellerTuple::class.java)
                .setParameter("cursor", decodeCursor(query.continuationToken))
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
                                contactEmail = null,
                            ),
                    )
                }.toTypedArray()

        val next = if (results.size <= pageSize) null else results.last().product.dataKey
        return PageCarrier(items, encodeCursor(next))
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

    private fun encodeCursor(cursor: Long?): String? {
        if (cursor == null) {
            return null
        }
        return cursor
            .toString()
            .toByteArray(UTF_8)
            .let { Base64.getEncoder().encodeToString(it) }
    }
}
