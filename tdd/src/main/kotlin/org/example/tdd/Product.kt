package org.example.tdd

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
class Product(
    val id: UUID,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dataKey: Long = 0L,
)
