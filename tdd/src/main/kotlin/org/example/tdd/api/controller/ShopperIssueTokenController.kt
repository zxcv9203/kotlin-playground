package org.example.tdd.api.controller

import io.jsonwebtoken.Jwts
import org.example.tdd.Shopper
import org.example.tdd.ShopperRepository
import org.example.tdd.api.JwtKeyHolder
import org.example.tdd.query.IssueShopperToken
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperIssueTokenController(
    private val shopperRepository: ShopperRepository,
    private val jwtKeyHolder: JwtKeyHolder,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping("/shopper/issueToken")
    fun issueToken(
        @RequestBody query: IssueShopperToken,
    ): ResponseEntity<AccessTokenCarrier> =
        shopperRepository
            .findByEmail(query.email)
            ?.takeIf { passwordEncoder.matches(query.password, it.hashedPassword) }
            ?.let { composeToken(it) }
            ?.let { AccessTokenCarrier(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    private fun composeToken(shopper: Shopper): String =
        Jwts
            .builder()
            .setSubject(shopper.id.toString())
            .signWith(jwtKeyHolder.key)
            .compact()
}
