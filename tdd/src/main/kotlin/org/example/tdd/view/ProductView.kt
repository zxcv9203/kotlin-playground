package org.example.tdd.view

import java.math.BigDecimal
import java.util.UUID

data class ProductView(
    val id: UUID,
    val seller: SellerView,
    val name: String,
    val imageUri: String,
    val description: String,
    val priceAmount: BigDecimal,
    val stockQuantity: Int,
)
