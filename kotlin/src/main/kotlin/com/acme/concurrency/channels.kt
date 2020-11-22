package com.acme.concurrency

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.EmptyCoroutineContext

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    demoPipes()
    val channel = Channel<Int>()
    launch {
        for (i in 0..5) {
            delay(100)
            channel.send(i)
        }
        channel.close()
    }

    for (i in channel) println("[Received]: $i")

    println("Done.")
}

// Pipelines

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers() = produce(EmptyCoroutineContext, 5) {
    var x = 1
    for (i in 1..5) {
        delay(1_000)
        println("[Produce number]: $i")
        send(x++)
    }
}

@ExperimentalCoroutinesApi
fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce(EmptyCoroutineContext, 5) {
    for (x in numbers) {
        delay(500)
        println("[Square number]: $x to ${x * x}")
        send(x * x)
    }
}

@ExperimentalCoroutinesApi
suspend fun demoPipes() = coroutineScope {
    val numbers = produceNumbers()
    val squares = square(numbers)

    println("Preparing before consuming.")
    delay(5_000)
    println("Started consuming.")
    squares.consumeEach { println("[Consumed]: $it") }
    println("Done.")
}