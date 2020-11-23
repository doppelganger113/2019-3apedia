package com.acme.exercises.http

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

const val EMOJI = "🦄"

fun printFromThread(msg: String) =
    println("[${Thread.currentThread().name}]: $msg")

suspend fun searchItemsForWords(itemIds: List<Int>, words: List<String>) = coroutineScope {
    val api = HackerNewsApi()
    val itemsChannel = Channel<Item>()
    val foundItemsChannel = Channel<Item>()
    printFromThread("STARTED")

    launch(Dispatchers.Default) {
        printFromThread("Launched fetching of items")
        itemIds
            .map {
                async {
                    printFromThread("Requesting item: $it")
                    val item = api.fetchItem(it)
                    printFromThread("Sending item ${item.id} to channel")
                    itemsChannel.send(item)
                }
            }
            .map { it.await() }
        itemsChannel.close()
    }

    printFromThread("Started channel listening")
    launch(Dispatchers.Default) {
        printFromThread("Launched processing")
        processItemsConcurrently(words, itemsChannel, foundItemsChannel)
        foundItemsChannel.close()
    }

    printFromThread("Listening for processed data")
    for (item in foundItemsChannel) {
        printFromThread("[Found]: $item")
    }

    // Fetch items
    // Send results to items channel
    // Process results by a set of coroutines
    // Send processed results to a results channel
    printFromThread("Done")
}

suspend fun processItemsConcurrently(
    words: List<String>,
    itemsChannel: ReceiveChannel<Item>,
    resultChannel: SendChannel<Item>,
    numOfProcessors: Int = 5
) = coroutineScope {
    repeat(numOfProcessors) {
        launch {
            for (item in itemsChannel) {
                val updated = updateItemIfHasWord(item, words)
                if (updated != null) {
                    resultChannel.send(updated)
                }
            }
        }
    }
}

infix fun String.getRelaxedIndex(word: String) =
    this.toLowerCase().indexOf(word.toLowerCase())

infix fun String.updateIfHasWord(word: String): String? {
    val indexOfWord = this getRelaxedIndex word
    if (indexOfWord == -1) {
        return null
    }

    return StringBuilder(this)
        .apply { insert(indexOfWord, EMOJI) }
        .toString()
}

fun updateItemIfHasWord(item: Item, words: List<String>): Item? {
    words.forEach {
        val updatedText = item.text updateIfHasWord it
        if (updatedText != null) {
            return item.copy(text = updatedText)
        }

        val updatedTitle = item.title updateIfHasWord it
        if (updatedTitle != null) {
            return item.copy(title = updatedTitle)
        }
    }

    return null
}