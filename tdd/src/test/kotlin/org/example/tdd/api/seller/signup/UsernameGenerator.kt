package org.example.tdd.api.seller.signup

import java.util.*

object UsernameGenerator {
    fun generate() = "username${UUID.randomUUID()}"
}
