package programmers.level0.l181929

fun solution(num_list: IntArray): Int {
    val gop = num_list.reduce { acc, i -> acc * i }
    val hap = num_list.sum().let { it * it }

    return if (gop < hap) 1 else 0
}
