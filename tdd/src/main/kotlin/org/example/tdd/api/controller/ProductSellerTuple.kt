package org.example.tdd.api.controller

import org.example.tdd.Product
import org.example.tdd.Seller

internal data class ProductSellerTuple(
    val product: Product,
    val seller: Seller,
)
