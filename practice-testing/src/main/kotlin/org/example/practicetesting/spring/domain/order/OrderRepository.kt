package org.example.practicetesting.spring.domain.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface OrderRepository : JpaRepository<Order, Long> {
    @Query(
        """
        SELECT o
        FROM Order o
        WHERE o.registeredDateTime >= :startDateTime
        AND o.registeredDateTime < :endDateTime
        AND o.orderStatus = :orderStatus
    """,
    )
    fun findOrdersBy(
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime,
        orderStatus: OrderStatus,
    ): List<Order>
}
