package org.example.tdd

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ShopperRepository : JpaRepository<Shopper, Long> {
    fun findByEmail(email: String): Shopper?

    fun findById(id: UUID): Shopper?
}
