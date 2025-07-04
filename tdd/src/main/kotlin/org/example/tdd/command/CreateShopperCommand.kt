package org.example.tdd.command

data class CreateShopperCommand(
    val email: String?,
    val username: String?,
    val password: String?,
)
