package org.example.tdd.command

import java.math.BigDecimal

data class RegisterProductCommand(
    val name: String,
    val imageUri: String,
    val description: String,
    val priceAmount: BigDecimal,
    val stockQuantity: Int,
)
