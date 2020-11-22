package com.acme.exercises.http

import kotlinx.coroutines.future.await
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

fun request(url: String): HttpRequest = HttpRequest.newBuilder()
    .timeout(Duration.ofSeconds(10))
    .header("Content-Type", "application/json")
    .uri(URI.create(url))
    .GET()
    .build()

private fun buildHttpClient(): HttpClient = HttpClient.newBuilder().build()

class HackerNewsApi(
    val domain: String = "https://hacker-news.firebaseio.com",
    val client: HttpClient = buildHttpClient()
)

suspend fun HackerNewsApi.fetchItem(id: Int): Item {
    return client.sendAsync(
        request("${this.domain}/v0/item/$id.json"),
        HttpResponse.BodyHandlers.ofString()
    )
        .thenApply(HttpResponse<String>::body)
        .thenApply(String::toItem)
        .await()
}