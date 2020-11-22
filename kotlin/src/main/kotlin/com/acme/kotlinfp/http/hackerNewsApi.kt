package com.acme.kotlinfp.http

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

fun createClient(): HttpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .followRedirects(HttpClient.Redirect.NORMAL)
    .connectTimeout(Duration.ofSeconds(20))
    .build()

fun threadPrint(msg: Any) {
    println("[${Thread.currentThread().name}] $msg")
}

suspend fun HttpClient.fetchItem(id: Int): String = suspendCoroutine { cont ->
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/$id.json"))
        .build()

    threadPrint("[$id] Fetching...")
    this.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse<String>::body)
        .thenApply {
            threadPrint("[$id] Retrieved.")
            cont.resumeWith(Result.success(it))
        }
}

suspend fun HttpClient.getItems(vararg items: Int): List<String> = coroutineScope {
    items.map {
        this@getItems.fetchItem(it)
    }
}

suspend fun HttpClient.getItemsParallel(vararg items: Int): List<String> = coroutineScope {
    items.map { async { this@getItemsParallel.fetchItem(it) } }
        .map { it.await() }
}

fun main(args: Array<String>) = runBlocking {
    val client = createClient()
    threadPrint("Started...")
    val time = measureTimeMillis {
        val items = client.getItems(8863, 121003, 2921983, 192327)
        threadPrint("Got items:")
        for (item in items) {
            println("Item: $item")
        }
    }
    threadPrint("Done in $time.")
}