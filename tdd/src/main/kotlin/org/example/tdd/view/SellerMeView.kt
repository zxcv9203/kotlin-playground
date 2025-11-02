package org.example.tdd.view

import java.util.UUID

data class SellerMeView(
    val id: UUID,
    val email: String,
    val username: String,
    val contactEmail: String?,
)
