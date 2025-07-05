package org.example.tdd

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Shopper(
    @Column(unique = true)
    val email: String,
    @Column(unique = true)
    val username: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dataKey: Long = 0L,
)
