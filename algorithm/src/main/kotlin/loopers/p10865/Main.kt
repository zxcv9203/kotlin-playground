package loopers.p10865

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val count = IntArray(n + 1)

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        count[a]++
        count[b]++
    }

    for (i in 1..n) println(count[i])
}
