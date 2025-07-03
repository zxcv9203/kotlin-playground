package org.example.tdd.api.controller

import org.example.tdd.result.AccessTokenCarrier
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SellerIssueTokenController {
    @PostMapping("/seller/issueToken")
    fun issueToken(): AccessTokenCarrier = AccessTokenCarrier("token")
}
