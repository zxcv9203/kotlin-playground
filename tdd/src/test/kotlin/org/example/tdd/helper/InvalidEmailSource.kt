package org.example.tdd.helper

import org.junit.jupiter.params.provider.MethodSource

@Retention(AnnotationRetention.RUNTIME)
@MethodSource("org.example.tdd.TestDataSource#invalidEmails")
annotation class InvalidEmailSource
