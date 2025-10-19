package org.example.tdd.api.controller

import jakarta.persistence.EntityManager
import org.example.tdd.query.GetProductPage
import org.example.tdd.querymodel.GetProductPageQueryProcessor
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperProductsController(
    private val entityManager: EntityManager,
) {
    @GetMapping("/shopper/products")
    fun getProducts(
        @RequestParam(required = false) continuationToken: String?,
    ): PageCarrier<ProductView> {
        val processor = GetProductPageQueryProcessor(entityManager)
        val query = GetProductPage(continuationToken)
        return processor.process(query)
    }
}
