package org.example.tdd.api

import org.springframework.boot.test.web.client.TestRestTemplate

class TestFixture(
    val client: TestRestTemplate,
)
