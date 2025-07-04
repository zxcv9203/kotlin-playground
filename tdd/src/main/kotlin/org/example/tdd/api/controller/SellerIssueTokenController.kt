package org.example.tdd.api.controller

import io.jsonwebtoken.Jwts
import org.example.tdd.SellerRepository
import org.example.tdd.query.IssueSellerToken
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.crypto.spec.SecretKeySpec

@RestController
class SellerIssueTokenController(
    @Value("\${security.jwt.secret}")
    private val jwtSecret: String,
    private val sellerRepository: SellerRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping("/seller/issueToken")
    fun issueToken(
        @RequestBody query: IssueSellerToken,
    ): ResponseEntity<AccessTokenCarrier> =
        sellerRepository
            .findByEmail(query.email)
            ?.takeIf { passwordEncoder.matches(query.password, it.hashedPassword) }
            ?.let { composeToken() }
            ?.let { AccessTokenCarrier(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    private fun composeToken(): String =
        Jwts
            .builder()
            .signWith(SecretKeySpec(jwtSecret.toByteArray(), "HmacSHA256"))
            .compact()
}
