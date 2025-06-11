package org.example.practicetesting.spring.domain.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllBySellingStatusIn(sellingStatuses: List<ProductSellingStatus>): List<Product>
}
