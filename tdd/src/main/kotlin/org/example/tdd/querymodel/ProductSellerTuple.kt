package org.example.tdd.querymodel

import org.example.tdd.Product
import org.example.tdd.Seller

internal data class ProductSellerTuple(
    val product: Product,
    val seller: Seller,
)
