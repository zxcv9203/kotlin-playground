package org.example.practicetesting.spring.domain.order

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.example.practicetesting.spring.domain.BaseEntity
import org.example.practicetesting.spring.domain.orderproduct.OrderProduct
import org.example.practicetesting.spring.domain.product.Product
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Enumerated(EnumType.STRING)
    val orderStatus: OrderStatus,
    val totalPrice: Int,
    val registeredDateTime: LocalDateTime,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val orderProducts: MutableList<OrderProduct> = mutableListOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) : BaseEntity() {
    companion object {
        fun create(
            products: List<Product>,
            registeredDateTime: LocalDateTime,
        ): Order {
            val order =
                Order(
                    orderStatus = OrderStatus.INIT,
                    totalPrice = products.sumOf { it.price },
                    registeredDateTime = registeredDateTime,
                    orderProducts = mutableListOf(),
                )

            val orderProducts = products.map { OrderProduct(order = order, product = it) }
            order.orderProducts.addAll(orderProducts)

            return order
        }
    }
}
