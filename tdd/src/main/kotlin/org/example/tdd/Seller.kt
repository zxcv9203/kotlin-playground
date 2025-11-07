package org.example.tdd

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.example.tdd.api.controller.SellerMeController
import java.util.UUID

@Entity
@Table(name = "seller")
class Seller(
    @Column(name = "username", nullable = false, unique = true)
    val username: String,
    @Column(name = "email", nullable = false, unique = true)
    val email: String,
    @Column(name = "hashed_password", nullable = false, length = 1000)
    val hashedPassword: String,
    val contactEmail: String,
    @Column(unique = true)
    val id: UUID,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dataKey: Long = 0,
)
