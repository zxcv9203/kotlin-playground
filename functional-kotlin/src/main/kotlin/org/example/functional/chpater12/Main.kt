package org.example.functional.chpater12

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchIn(scope: CoroutineScope): Job =
    scope.launch { collect() }

suspend fun main() = coroutineScope {
    flowOf(1, 2, 3)
        .onEach { print(it) }
        .launchIn(this)
}