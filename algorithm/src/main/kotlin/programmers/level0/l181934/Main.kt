package programmers.level0.l181934

fun solution(
    ineq: String,
    eq: String,
    n: Int,
    m: Int,
): Int {
    val compare = ineq + eq

    return when (compare) {
        ">=" -> if (n >= m) 1 else 0
        "<=" -> if (n <= m) 1 else 0
        ">!" -> if (n > m) 1 else 0
        "<!" -> if (n < m) 1 else 0
        else -> 0
    }
}
