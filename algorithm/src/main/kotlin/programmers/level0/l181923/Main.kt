package programmers.level0.l181923

fun solution(
    arr: IntArray,
    queries: Array<IntArray>,
): IntArray =
    IntArray(queries.size) { index ->
        val (s, e, k) = queries[index]
        (s..e)
            .asSequence()
            .map { arr[it] }
            .filter { it > k }
            .minOrNull() ?: -1
    }

fun main() {
    val arr = intArrayOf(0, 1, 2, 4, 3)
    val queries = arrayOf(intArrayOf(0, 4, 2), intArrayOf(0, 3, 2), intArrayOf(0, 2, 2))
    println(solution(arr, queries).toList())
}
