package loopers.p1407

fun main() {
    val (a, b) = readln().split(" ").map { it.toLong() }

    val extra = generateSequence(2L to 1L) { (d, p) -> d * 2 to p * 2 }
        .takeWhile { (d, _) -> d <= b }
        .sumOf { (d, p) -> (b / d - (a - 1) / d) * p }

    println(b - a + 1 + extra)
}
