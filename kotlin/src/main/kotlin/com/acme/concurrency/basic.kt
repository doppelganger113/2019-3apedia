package com.acme.concurrency

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(200)
        println("First")
    }

    coroutineScope {
        launch { helloWorld() }

        delay(100)
        println("Third")
    }

    println("Coroutine scope is over.")
}

suspend fun helloWorld() {
    delay(500)
    println("Second")
}