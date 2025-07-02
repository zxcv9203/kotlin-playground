package org.example.tdd.api.seller.signup

import java.util.*

object EmailGenerator {
    fun generateEmail(): String = UUID.randomUUID().toString() + "@test.com"
}
