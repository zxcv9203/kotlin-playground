package org.example.practicetesting.spring.domain.stock

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.example.practicetesting.spring.domain.BaseEntity

@Entity
class Stock(
    val productNumber: String,
    var quantity: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) : BaseEntity() {
    fun isQuantityLessThan(quantity: Int): Boolean = this.quantity < quantity

    fun deductQuantity(quantity: Int) {
        if (isQuantityLessThan(quantity)) {
            throw IllegalArgumentException("차감할 재고 수량이 없습니다.")
        }
        this.quantity -= quantity
    }

    companion object {
        fun create(
            productNumber: String,
            quantity: Int,
        ) = Stock(productNumber, quantity)
    }
}
