package org.example.tdd

import org.springframework.data.jpa.repository.JpaRepository

interface ShopperRepository : JpaRepository<Shopper, Long> {
    fun findByEmail(email: String): Shopper?
}
