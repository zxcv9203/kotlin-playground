package org.example.practicetesting.spring.domain.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllBySellingStatusIn(sellingStatuses: List<ProductSellingStatus>): List<Product>

    fun findAllByProductNumberIn(productNumbers: List<String>): List<Product>

    @Query(
        """
       select p.product_number
        from product p
        order by id desc limit 1
    """,
        nativeQuery = true,
    )
    fun findLatestProductNumber(): String?
}
