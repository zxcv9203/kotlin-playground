package programmers.level0.l181930

fun solution(
    a: Int,
    b: Int,
    c: Int,
): Int {
    val nums = listOf(a, b, c)
    val sum1 = nums.sum()
    val sum2 = nums.sumOf { it * it }
    val sum3 = nums.sumOf { it * it * it }

    return when (nums.toSet().size) {
        1 -> sum1 * sum2 * sum3
        2 -> sum1 * sum2
        else -> sum1
    }
}
