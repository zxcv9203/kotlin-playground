package org.example.tdd.api.controller

import io.jsonwebtoken.Jwts
import org.example.tdd.ShopperRepository
import org.example.tdd.api.JwtKeyHolder
import org.example.tdd.query.IssueShopperToken
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperIssueTokenController(
    private val shopperRepository: ShopperRepository,
    private val jwtKeyHolder: JwtKeyHolder,
) {
    @PostMapping("/shopper/issueToken")
    fun issueToken(
        @RequestBody query: IssueShopperToken,
    ): ResponseEntity<AccessTokenCarrier> =
        shopperRepository
            .findByEmail(query.email)
            ?.let { composeToken() }
            ?.let { AccessTokenCarrier(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    private fun composeToken(): String =
        Jwts
            .builder()
            .signWith(jwtKeyHolder.key)
            .compact()
}
