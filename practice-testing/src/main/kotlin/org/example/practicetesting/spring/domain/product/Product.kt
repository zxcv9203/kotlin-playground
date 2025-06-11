package org.example.practicetesting.spring.domain.product

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Product(
    val productNumber: String,
    @Enumerated(EnumType.STRING)
    val type: ProductType,
    @Enumerated(EnumType.STRING)
    val sellingStatus: ProductSellingStatus,
    val name: String,
    val price: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
)
