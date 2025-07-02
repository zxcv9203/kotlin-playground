package org.example.tdd

import org.springframework.data.jpa.repository.JpaRepository

interface SellerRepository : JpaRepository<Seller, Long>
