package org.example.tdd.api.controller

import org.example.tdd.query.IssueShopperToken
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ShopperIssueTokenController {
    @PostMapping("/shopper/issueToken")
    fun issueToken(query: IssueShopperToken): AccessTokenCarrier = AccessTokenCarrier("token")
}
