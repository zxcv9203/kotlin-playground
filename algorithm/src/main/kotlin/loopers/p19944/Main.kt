package loopers.p19944

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    if (m <= 2) {
        println("NEWBIE!")
    } else if (n >= m) {
        println("OLDBIE!")
    } else {
        println("TLE!")
    }
}
