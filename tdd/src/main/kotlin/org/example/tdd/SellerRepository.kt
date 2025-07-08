package org.example.tdd

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SellerRepository : JpaRepository<Seller, Long> {
    fun findByEmail(email: String?): Seller?

    fun findById(id: UUID): Seller?
}
