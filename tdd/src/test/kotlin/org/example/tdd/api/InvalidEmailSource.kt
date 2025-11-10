package org.example.tdd.api

import org.junit.jupiter.params.provider.MethodSource

@Retention(AnnotationRetention.RUNTIME)
@MethodSource("org.example.tdd.TestDataSource#invalidEmails")
annotation class InvalidEmailSource
