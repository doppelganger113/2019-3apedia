package com.acme.concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    val res = withTimeoutOrNull(1_500) {
        repeat(100) {
            println("[$it]: Processing...")
            delay(300)
        }

        "Done"
    }
    println("Result is: $res")
}