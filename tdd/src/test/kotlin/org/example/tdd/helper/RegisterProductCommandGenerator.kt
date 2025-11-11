package org.example.tdd.helper

import org.example.tdd.command.RegisterProductCommand
import java.math.BigDecimal
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

object RegisterProductCommandGenerator {
    fun generate(
        name: String = generateProductName(),
        imageUri: String = generateImageUri(),
        description: String = generateDescription(),
        priceAmount: BigDecimal = generatePriceAmount(),
        stockQuantity: Int = generateStockQuantity(),
    ) = RegisterProductCommand(
        name = name,
        imageUri = imageUri,
        description = description,
        priceAmount = priceAmount,
        stockQuantity = stockQuantity,
    )

    private fun generateProductName(): String = "name${UUID.randomUUID()}"

    private fun generateImageUri(): String = "https://test.com/images/${UUID.randomUUID()}"

    private fun generateDescription(): String = "description${UUID.randomUUID()}"

    private fun generatePriceAmount(): BigDecimal {
        val random = ThreadLocalRandom.current()
        return BigDecimal.valueOf(random.nextLong(10000, 100000))
    }

    private fun generateStockQuantity(): Int {
        val random = ThreadLocalRandom.current()
        return random.nextInt(10, 100)
    }
}
