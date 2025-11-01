package org.example.tdd.api.seller.signup

import java.security.SecureRandom

object PasswordGenerator {
    private val random = SecureRandom()

    fun generate(): String {
        val mixture = StringBuilder()

        for (i in 0..9) {
            mixture.append('A' + random.nextInt('Z' - 'A' + 1))
            mixture.append('0' + random.nextInt('9' - '0' + 1))
            mixture.append('a' + random.nextInt('z' - 'a' + 1))
        }

        return "password$mixture"
    }
}
