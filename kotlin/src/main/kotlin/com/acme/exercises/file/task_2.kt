package com.acme.exercises.file

/**
 * @description
 * Find all heroes the player played and how many games he won with them, sorted from most wins to least, eg:
 *  Shaman:  Wins 3 Losses 1
 *  Paladin: Wins 1 Losses 3
 *
 */
fun executeTask2(dump: DataDump) = selectWinsLossesPerHero(dump)
    .sortedByDescending { it.wins }
    .forEach { (hero, wins, losses) ->
        println("$hero: Wins $wins Losses $losses")
    }

enum class Hero(val hero: String) {
    Shaman("Shaman");

    companion object {
        fun from(value: String) = values()
            .firstOrNull { it.hero == value }
    }
}

data class HeroWinsLosses(
    val hero: String,
    val wins: Int = 0,
    val losses: Int = 0,
) {
    companion object {
        fun updateByResult(
            map: HeroWinsLosses, result: GameResult
        ): HeroWinsLosses {
            val wins = if (result == GameResult.Won) map.wins + 1 else map.wins
            val losses = if (result == GameResult.Loss) map.losses + 1 else map.losses

            return HeroWinsLosses(map.hero, wins, losses)
        }
    }
}

fun selectWinsLossesPerHero(dump: DataDump): List<HeroWinsLosses> {
    return dump.games
        .groupBy { it.hero }
        .map {
            it.value.fold(HeroWinsLosses(it.key), { acc, game ->
                HeroWinsLosses.updateByResult(acc, game.result)
            })
        }
}