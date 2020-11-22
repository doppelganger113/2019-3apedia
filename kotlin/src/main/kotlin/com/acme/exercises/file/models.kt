package com.acme.exercises.file

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class GameResult(val result: String) {
    @SerialName("win")
    Won("won"),

    @SerialName("loss")
    Loss("loss")
}

@Serializable
data class Card(
    val id: String,
    val name: String,
    val mana: Int
)

@Serializable
data class CardHistory(
    val player: String,
    val turn: Int,
    val card: Card
)

@Serializable
data class Game(
    val id: Int,
    val region: String,
    val mode: String,
    val hero: String,
    val opponent: String,
    val result: GameResult,

    @SerialName("card_history")
    val cardHistory: List<CardHistory>
)

@Serializable
data class DataDump(
    @SerialName("range_start")
    val rangeStart: String,

    @SerialName("range_end")
    val rangeEnd: String,

    @SerialName("unique_users")
    val uniqueUsers: Int,

    @SerialName("total_games")
    val totalGames: Int,

    val games: List<Game>
)