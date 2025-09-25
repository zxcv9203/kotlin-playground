package programmers.level1.l132267

fun solution(a: Int, b: Int, n: Int): Int {
    var emptyBottles = n
    var totalExchanged = 0

    while (emptyBottles >= a) {
        val newColas = emptyBottles / a * b
        emptyBottles = emptyBottles % a + newColas
        totalExchanged += newColas
    }

    return totalExchanged
}

fun main() {
    val a = 3
    val b = 1
    val n = 20
    println(solution(a, b, n))
}
