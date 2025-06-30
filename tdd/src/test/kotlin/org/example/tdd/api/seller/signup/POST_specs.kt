package org.example.tdd.api.seller.signup

import org.example.tdd.TddApplication
import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [TddApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
@DisplayName("POST /seller/signup")
class PostSpecs
