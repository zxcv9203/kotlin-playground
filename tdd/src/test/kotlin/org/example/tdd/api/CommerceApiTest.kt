package org.example.tdd.api

import org.example.tdd.TddApplication
import org.springframework.boot.test.context.SpringBootTest

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SpringBootTest(
    classes = [TddApplication::class, TestFixtureConfig::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
annotation class CommerceApiTest
