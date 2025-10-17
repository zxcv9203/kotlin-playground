package org.example.tdd.querymodel

import org.example.tdd.Product
import org.example.tdd.view.SellerProductView

class ProductMapper {
    companion object {
        fun convertToView(product: Product): SellerProductView =
            SellerProductView(
                id = product.id,
                name = product.name,
                description = product.description,
                priceAmount = product.priceAmount,
                imageUri = product.imageUri,
                stockQuantity = product.stockQuantity,
                registeredTimeUtc = product.registeredTimeUtc,
            )
    }
}
