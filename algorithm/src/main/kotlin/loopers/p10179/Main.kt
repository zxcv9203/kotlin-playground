package loopers.p10179

fun main() {
    val n = readln().toInt()

    List(n) { readln().toDouble() }
        .map { it * 0.8 }
        .forEach { println("$%.2f".format(it)) }
}
