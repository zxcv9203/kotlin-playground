package org.example.tdd.api.controller

import io.jsonwebtoken.Jwts
import org.example.tdd.api.JwtKeyHolder
import org.example.tdd.query.IssueShopperToken
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperIssueTokenController(
    private val jwtKeyHolder: JwtKeyHolder,
) {
    @PostMapping("/shopper/issueToken")
    fun issueToken(query: IssueShopperToken): AccessTokenCarrier =
        AccessTokenCarrier(
            Jwts
                .builder()
                .signWith(jwtKeyHolder.key)
                .compact(),
        )
}
