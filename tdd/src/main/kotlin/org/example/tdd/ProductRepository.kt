package org.example.tdd

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository : JpaRepository<Product, Long> {
    fun findById(id: UUID): Product?
}
