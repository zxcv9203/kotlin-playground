package org.example.practicetesting.spring.domain.orderproduct

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.example.practicetesting.spring.domain.BaseEntity
import org.example.practicetesting.spring.domain.order.Order
import org.example.practicetesting.spring.domain.product.Product

@Entity
class OrderProduct(
    @ManyToOne(fetch = FetchType.LAZY)
    val order: Order,
    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) : BaseEntity()
