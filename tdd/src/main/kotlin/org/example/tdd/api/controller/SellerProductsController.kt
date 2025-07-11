package org.example.tdd.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SellerProductsController {
    @PostMapping("/seller/products")
    fun registerProduct(): ResponseEntity<Unit> = ResponseEntity.status(HttpStatus.CREATED).build()
}
