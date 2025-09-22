package programmers.level0.l181935

fun solution(n: Int): Int = if (n.isEven()) n.evenSquareSum() else n.oddSum()

fun Int.isEven(): Boolean = this % 2 == 0

fun Int.evenSquareSum(): Int = (2..this step 2).sumOf { it * it }

fun Int.oddSum(): Int = (1..this step 2).sum()

fun main() {
    val n = 7
    println(solution(n))
}
