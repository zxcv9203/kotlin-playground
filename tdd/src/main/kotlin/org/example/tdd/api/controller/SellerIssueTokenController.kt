package org.example.tdd.api.controller

import io.jsonwebtoken.Jwts
import org.example.tdd.SellerRepository
import org.example.tdd.query.IssueSellerToken
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.crypto.spec.SecretKeySpec

@RestController
class SellerIssueTokenController(
    @Value("\${security.jwt.secret}")
    private val jwtSecret: String,
    private val sellerRepository: SellerRepository,
) {
    @PostMapping("/seller/issueToken")
    fun issueToken(
        @RequestBody query: IssueSellerToken,
    ): ResponseEntity<AccessTokenCarrier> {
        sellerRepository
            .findByEmail(query.email)
            ?: return ResponseEntity.badRequest().build()

        return AccessTokenCarrier(composeToken())
            .let { ResponseEntity.ok(it) }
    }

    private fun composeToken(): String =
        Jwts
            .builder()
            .signWith(SecretKeySpec(jwtSecret.toByteArray(), "HmacSHA256"))
            .compact()
}
