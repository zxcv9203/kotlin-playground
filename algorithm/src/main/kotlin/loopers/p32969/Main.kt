package loopers.p32969

fun main() {
    val academic =
        mapOf(
            "social" to "digital humanities",
            "history" to "digital humanities",
            "language" to "digital humanities",
            "literacy" to "digital humanities",
            "bigdata" to "public bigdata",
            "public" to "public bigdata",
            "society" to "public bigdata",
        )
    val s = readln().lowercase()

    val find = academic.keys.first { s.contains(it) }

    println(academic[find])
}
