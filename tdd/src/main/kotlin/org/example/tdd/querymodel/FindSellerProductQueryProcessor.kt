package org.example.tdd.querymodel

import org.example.tdd.Product
import org.example.tdd.query.FindSellerProduct
import org.example.tdd.view.SellerProductView
import java.util.UUID

class FindSellerProductQueryProcessor(
    private val findProduct: (id: UUID) -> Product?,
) {
    fun process(query: FindSellerProduct): SellerProductView? =
        findProduct(query.productId)
            ?.takeIf { it.sellerId == query.sellerId }
            ?.let { ProductMapper.convertToView(it) }
}
