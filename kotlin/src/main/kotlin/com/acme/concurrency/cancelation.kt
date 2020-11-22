package com.acme.concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(500) {
                println("I'm sleeping for $it")
                delay(500)
            }
        }
        finally {
            println("Guess I'm closed")
        }
    }

    delay(2_000)
    println("[main]: I'm tired of waiting.")
    job.cancel()
    job.join()
    println("[main]: Now I can quit.")
}