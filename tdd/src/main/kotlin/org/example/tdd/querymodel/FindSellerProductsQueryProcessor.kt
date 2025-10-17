package org.example.tdd.querymodel

import org.example.tdd.Product
import org.example.tdd.query.FindSellerProducts
import org.example.tdd.view.SellerProductView
import java.util.UUID

class FindSellerProductsQueryProcessor(
    private val findProductsBySellerId: (UUID) -> List<Product>,
) {
    fun process(query: FindSellerProducts): Array<SellerProductView> {
        val response =
            findProductsBySellerId(query.sellerId)
                .sortedByDescending { it.registeredTimeUtc }
                .map { ProductMapper.convertToView(it) }
                .toTypedArray()
        return response
    }
}
