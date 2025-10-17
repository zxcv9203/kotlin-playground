package org.example.tdd.query

import java.util.UUID

data class FindSellerProduct(
    val sellerId: UUID,
    val productId: UUID,
)
