package org.example.tdd.command

data class CreateSellerCommand(
    val email: String?,
    val password: String?,
    val username: String?,
    val contactEmail: String?,
)
