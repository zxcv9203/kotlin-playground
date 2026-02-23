package loopers.p23303

fun main() {
    readln()
        .contains("d2", ignoreCase = true)
        .let { if (it) "D2" else "unrated" }
        .let(::println)
}
