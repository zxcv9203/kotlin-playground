package org.example.tdd.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperProductsController {
    @GetMapping("/shopper/products")
    fun getProducts() {
    }
}
