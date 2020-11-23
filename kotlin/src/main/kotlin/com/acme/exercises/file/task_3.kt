package com.acme.exercises.file

data class TaskThreeResult(
    val totalManaSpent: Int,
    val player: String,
    val opponent: String,
    val opponentTotalManaSpent: Int,
    val wasPlayerFirst: Boolean
)

fun mapPairToTaskThreeResult(totalManaSpent: Int, game: Game): TaskThreeResult {
    val wasPlayerFirst = game.cardHistory.firstOrNull()?.turn == 1
    val opponentTotalManaSpent = totalManaSpent(
        game.cardHistory, TargetPlayer.Opponent
    )

    return TaskThreeResult(
        totalManaSpent,
        game.hero,
        game.opponent,
        opponentTotalManaSpent,
        wasPlayerFirst
    )
}

fun sumTotalMana(totalManaSpent: Int, cardHistory: CardHistory) =
    totalManaSpent + cardHistory.card.mana

fun totalManaSpent(cardHistory: List<CardHistory>, targetPlayer: TargetPlayer): Int =
    cardHistory
        .filter { it.player == targetPlayer.value }
        .fold(0, ::sumTotalMana)

// Find 5 games where player won, sorted by the total of mana spent on cards.
// Display:
//  - total number of mana spent
//  - Player Hero
//  - Opponent Hero
//  - Opponent total mana spent on cards
//  - Has Player played first that game
fun executeTask3(dump: DataDump, limit: Int = 5): List<TaskThreeResult> =
    dump.games
        .map {
            Pair(
                totalManaSpent(it.cardHistory, TargetPlayer.Player),
                it
            )
        }
        .sortedByDescending { it.first }
        .take(limit)
        .map { mapPairToTaskThreeResult(it.first, it.second) }
