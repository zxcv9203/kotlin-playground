package org.example.practicetesting.spring.domain.stock

import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {
    fun findAllByProductNumberIn(productNumbers: List<String>): List<Stock>
}
