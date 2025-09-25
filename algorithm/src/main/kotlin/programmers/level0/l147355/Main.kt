package programmers.level0.l147355

fun solution(t: String, p: String): Int =
    t.windowed(p.length).count { it <= p }

fun main() {
    val t = "100"
    val p = "23"
    println(solution(t, p))
}
