package loopers.p2756

import kotlin.math.hypot

typealias Darts = List<Double>

private val rings = listOf(3.0 to 10, 6.0 to 8, 9.0 to 6, 12.0 to 4, 15.0 to 2)

fun Darts.totalScore(): Int =
    this
        .chunked(2)
        .sumOf { (x, y) -> scoreOf(x, y) } * 10

fun scoreOf(
    x: Double,
    y: Double,
): Int {
    val dist = hypot(x, y)

    return rings
        .firstOrNull { (r, _) -> dist <= r }
        ?.second ?: 0
}

fun main() {
    val n = readln().toInt()

    repeat(n) {
        val scores = readln().split(" ").map { it.toDouble() }

        val p1Scores =
            scores
                .subList(0, 6)
                .totalScore()

        val p2Scores =
            scores
                .subList(6, 12)
                .totalScore()

        val result =
            when {
                p1Scores > p2Scores -> "PLAYER 1 WINS."
                p2Scores > p1Scores -> "PLAYER 2 WINS."
                else -> "TIE."
            }

        println("SCORE: $p1Scores to $p2Scores, $result")
    }
}
