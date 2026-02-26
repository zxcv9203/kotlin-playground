package loopers.p34977

fun main() {
    val n = readln().toInt()

    val seq = readln().split(" ")
    println(if ((1..n / 2).any { seq.take(it) == seq.takeLast(it) }) "yes" else "no")
}
