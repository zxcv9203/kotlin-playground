package programmers.level0.l181931

fun solution(
    a: Int,
    d: Int,
    included: BooleanArray,
): Int =
    included
        .withIndex()
        .filter { it.value }
        .sumOf { a + d * it.index }
