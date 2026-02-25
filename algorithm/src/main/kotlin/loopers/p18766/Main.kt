package loopers.p18766

fun main() {
    val t = readln().toInt()

    repeat(t) {
        val n = readln().toInt()
        val origin = readln().split(" ").sorted()
        val target = readln().split(" ").sorted()

        println(if (origin != target) "CHEATER" else "NOT CHEATER")
    }
}
