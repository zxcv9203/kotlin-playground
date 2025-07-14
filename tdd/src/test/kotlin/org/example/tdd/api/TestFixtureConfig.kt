package org.example.tdd.api

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import org.springframework.core.env.Environment

@TestConfiguration
class TestFixtureConfig {
    @Bean
    @Scope("prototype")
    fun testFixture(environment: Environment): TestFixture = TestFixture.create(environment)
}
