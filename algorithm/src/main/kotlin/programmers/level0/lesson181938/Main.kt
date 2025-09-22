package programmers.level0.lesson181938

fun solution(
    a: Int,
    b: Int,
): Int =
    maxOf(
        "$a$b".toInt(),
        2 * a * b,
    )

fun main() {
    val a = 11
    val b = 3
    println(solution(a, b))
}
