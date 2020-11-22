package com.acme.concurrency

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun log(msg: String): Unit = println("[${Thread.currentThread().name}]: $msg")

fun emitNumbers(total: Int): Flow<Int> = flow {
    for (i in 0..total) {
        Thread.sleep(500)
        log("emitting $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)

fun main() = runBlocking<Unit> {
    launch {
        for (i in 0..5) {
            delay(100)
            log("Doing some work on the side")
        }
    }

    emitNumbers(10)
        .collect { log(it.toString()) }
}