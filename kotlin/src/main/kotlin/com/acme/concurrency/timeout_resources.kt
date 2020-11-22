package com.acme.concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired = 0

class Resource {
    init {
        acquired++
    }

    fun close() {
        acquired--
    }
}

fun main() = runBlocking {
    repeat(1_000_000) {
        launch {
            val resource = withTimeout(60) {
                delay(50)
                Resource()
            }
            resource.close()
        }
    }
    println(acquired)
}