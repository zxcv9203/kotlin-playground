package org.example.tdd.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ThrowingConsumer
import java.util.*

object JwtAssertions {
    fun conformsToJwtFormat(): ThrowingConsumer<String> =
        ThrowingConsumer { jwt ->
            val parts = jwt.split(".")
            assertThat(parts).hasSize(3)
            assertThat(parts[0]).matches(JwtAssertions::isBase64EncodedJson)
            assertThat(parts[1]).matches(JwtAssertions::isBase64EncodedJson)
            assertThat(parts[2]).matches(JwtAssertions::isBase64Encoded)
        }

    fun isBase64EncodedJson(s: String): Boolean {
        try {
            ObjectMapper().readTree(Base64.getUrlDecoder().decode(s))
            return true
        } catch (_: Exception) {
            return false
        }
    }

    fun isBase64Encoded(s: String): Boolean {
        try {
            Base64.getUrlDecoder().decode(s)
            return true
        } catch (_: Exception) {
            return false
        }
    }
}
