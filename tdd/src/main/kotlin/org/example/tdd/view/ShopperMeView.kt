package org.example.tdd.view

import java.util.UUID

data class ShopperMeView(
    val id: UUID,
    val email: String,
    val username: String,
)
