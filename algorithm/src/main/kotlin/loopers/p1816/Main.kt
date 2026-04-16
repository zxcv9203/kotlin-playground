package loopers.p1816

fun main() {
    repeat(readln().toInt()) {
        val s = readln().toLong()
        val isValid = (2L..1_000_000L).none { s % it == 0L }
        println(if (isValid) "YES" else "NO")
    }
}
