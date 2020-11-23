package com.acme.exercises.file

enum class TargetPlayer(val value: String) {
    Player("me"),
    Opponent("opponent")
}

fun executeTask1(dump: DataDump, targetPlayer: TargetPlayer) =
    dump.games
        .map { game ->
            game.cardHistory
                .filter {
                    it.player == targetPlayer.value
                }
                .map { it.card.name }
        }
        .flatten()
        .distinct()