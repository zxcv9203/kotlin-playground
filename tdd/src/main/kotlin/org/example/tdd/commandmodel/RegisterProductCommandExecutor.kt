package org.example.tdd.commandmodel

import org.example.tdd.Product
import org.example.tdd.command.RegisterProductCommand
import org.example.tdd.model.command.InvalidCommandException
import java.net.URI
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID
import java.util.function.Consumer

class RegisterProductCommandExecutor(
    private val saveProduct: Consumer<Product>,
) {
    fun execute(
        id: UUID,
        sellerId: UUID,
        command: RegisterProductCommand,
    ) {
        validateCommand(command)
        val product = createProduct(id, sellerId, command)
        saveProduct(product)
    }

    private fun validateCommand(command: RegisterProductCommand) {
        if (!isValidUri(command.imageUri)) {
            throw InvalidCommandException()
        }
    }

    private fun createProduct(
        id: UUID,
        sellerId: UUID,
        command: RegisterProductCommand,
    ): Product {
        val product =
            Product(
                id = id,
                sellerId = sellerId,
                name = command.name,
                description = command.description,
                priceAmount = command.priceAmount,
                imageUri = command.imageUri,
                stockQuantity = command.stockQuantity,
                registeredTimeUtc = LocalDateTime.now(ZoneOffset.UTC),
            )
        return product
    }

    private fun saveProduct(product: Product) {
        saveProduct.accept(product)
    }

    private fun isValidUri(imageUri: String): Boolean {
        try {
            val uri = URI.create(imageUri)
            return uri.host != null
        } catch (_: IllegalArgumentException) {
            return false
        }
    }
}
