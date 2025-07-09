package org.example.tdd.api

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestFixtureConfig {
    @Bean
    fun testFixture(client: TestRestTemplate): TestFixture = TestFixture(client)
}
