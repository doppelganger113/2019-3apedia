package com.acme.concurrency

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun fakeRequest(): Int {
    delay(1_000)
    return 24
}

suspend fun fakeAnotherRequest(): Int {
    delay(1_000)
    return 10
}

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { fakeRequest() }
        val two = async(start = CoroutineStart.LAZY) { fakeAnotherRequest() }
        one.start()
        two.start()

        println("The results are: ${one.await()} and ${two.await()}")
    }
    println("Completed in $time ms")
}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { fakeRequest() }
    val two = async { fakeAnotherRequest() }

    one.await() + two.await()
}