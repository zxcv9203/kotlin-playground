package org.example.tdd.view

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class SellerProductView(
    val id: UUID,
    val name: String,
    val imageUri: String,
    val description: String,
    val priceAmount: BigDecimal,
    val stockQuantity: Int,
    val registeredTimeUtc: LocalDateTime,
)
