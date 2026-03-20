package loopers.p32751

typealias Burger = String

fun Burger.isTasty(limit: Map<Char, Int>): Boolean {
    if (this.first() != 'a' || this.last() != 'a') return false
    if (this.windowed(2).any { it[0] == it[1] }) return false
    if (this.groupingBy { it }.eachCount().any { (ch, cnt) -> cnt > (limit[ch] ?: 0) }) return false

    return true
}

fun main() {
    val n = readln().toInt()
    val (a, b, c, d) = readln().split(" ").map { it.toInt() }
    val burger: Burger = readln()

    val limit = mapOf('a' to a, 'b' to b, 'c' to c, 'd' to d)
    println(if (burger.isTasty(limit)) "Yes" else "No")
}
