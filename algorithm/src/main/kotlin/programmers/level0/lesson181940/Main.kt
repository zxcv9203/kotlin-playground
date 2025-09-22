package programmers.level0.lesson181940

fun solution(
    my_string: String,
    k: Int,
): String = my_string.repeat(k)

fun main() {
    val a = "string"
    val k = 3
    println(solution(a, k))
}
