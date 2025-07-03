package org.example.tdd.api.controller

import io.jsonwebtoken.Jwts
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.crypto.spec.SecretKeySpec

@RestController
class SellerIssueTokenController(
    @Value("\${security.jwt.secret}")
    private val jwtSecret: String,
) {
    @PostMapping("/seller/issueToken")
    fun issueToken(): AccessTokenCarrier =
        AccessTokenCarrier(
            Jwts
                .builder()
                .signWith(SecretKeySpec(jwtSecret.toByteArray(), "HmacSHA256"))
                .compact(),
        )
}
