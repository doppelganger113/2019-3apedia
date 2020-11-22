package com.acme.exercises.http

import kotlinx.serialization.*
import kotlinx.serialization.json.*

val jsonFormatter = Json { ignoreUnknownKeys = true }

@Serializable
data class Item(
    val id: Int,
    val by: String,
    val time: Int,
    val type: String,

    val descendants: Int = 0,
    val parent: Int = 0,
    val kids: List<Int> = emptyList(),
    val score: Int = 0,
    val title: String = "",
    val text: String = "",
    val url: String = "",
)

fun String.toItem(): Item = jsonFormatter.decodeFromString(this)
