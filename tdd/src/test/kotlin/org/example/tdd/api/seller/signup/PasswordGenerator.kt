package org.example.tdd.api.seller.signup

import java.util.UUID

object PasswordGenerator {
    fun generate(): String = "password${UUID.randomUUID()}"
}
