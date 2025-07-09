package org.example.tdd

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
class Shopper(
    @Column(unique = true)
    val email: String,
    @Column(unique = true)
    val username: String,
    @Column(name = "hashed_password", nullable = false, length = 1000)
    val hashedPassword: String,
    @Column(unique = true)
    val id: UUID,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dataKey: Long = 0L,
)
