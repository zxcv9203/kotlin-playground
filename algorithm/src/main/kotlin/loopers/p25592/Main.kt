package loopers.p25592

fun main() {
    var n = readln().toInt()
    var turn = 1
    while (n >= turn) {
        n -= turn
        turn++
    }
    println(if (turn % 2 == 0) 0 else turn - n)
}
