package org.example.tdd

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(
    indexes = [Index(columnList = "sellerId")],
)
class Product(
    @Column(unique = true)
    val id: UUID,
    val sellerId: UUID,
    val name: String,
    val imageUri: String,
    val description: String,
    val priceAmount: BigDecimal,
    val stockQuantity: Int,
    val registeredTimeUtc: LocalDateTime,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dataKey: Long = 0L,
)
