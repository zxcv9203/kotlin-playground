package programmers.level0.lesson181940

fun solution(
    a: Int,
    b: Int,
): Int =
    maxOf(
        "$a$b".toInt(),
        "$b$a".toInt(),
    )

fun main() {
    val a = "string"
    val k = 3
    println(solution(11, 3))
}
