package org.example.tdd.command

data class CreateSellerCommand constructor(
    val email: String?,
    val password: String?,
    val username: String?,
)
