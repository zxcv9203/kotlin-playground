package org.example.tdd.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SellerIssueTokenController {
    @PostMapping("/seller/issueToken")
    fun issueToken() {
    }
}
