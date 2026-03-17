package loopers.p11024

fun main() {
    val n = readln().toInt()

    repeat(n) {
        val numbers = readln().split(" ").map { it.toInt() }
        println(numbers.sum())
    }
}
