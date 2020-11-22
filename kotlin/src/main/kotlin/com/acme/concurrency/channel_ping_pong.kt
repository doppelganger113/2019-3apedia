package com.acme.concurrency

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Ball(var hits: Int)

fun main() = runBlocking {
    val table = Channel<Ball>()
    launch { player("John", table) }
    launch { player("Alexa", table) }
    table.send(Ball(0))

    delay(5_000)
    coroutineContext.cancelChildren()
    println("Done.")
}

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) {
        ball.hits++
        println("$name ${ball.hits}")
        delay(500)
        table.send(ball)
    }
}
