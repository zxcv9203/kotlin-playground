package org.example.practicetesting.spring.api.service.product.response

import org.example.practicetesting.spring.domain.product.Product
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType

data class ProductResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val type: ProductType,
    val sellingStatus: ProductSellingStatus,
) {
    companion object {
        fun of(product: Product): ProductResponse =
            ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                type = product.type,
                sellingStatus = product.sellingStatus,
            )
    }
}
