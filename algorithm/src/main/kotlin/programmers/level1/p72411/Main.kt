package programmers.level1.p72411

fun solution(
    orders: Array<String>,
    course: IntArray,
): Array<String> =
    course.flatMap { size ->
        val counts = orders
            .flatMap { it.toSortedString().combinations(size) }
            .groupingBy { it }
            .eachCount()

        val max = counts.values.maxOrNull() ?: 0
        counts.filterValues { it == max && max >= 2 }.keys
    }.sorted().toTypedArray()

fun String.toSortedString() = toCharArray().sorted().joinToString("")

fun String.combinations(size: Int): List<String> =
    indices.toList().combinations(size).map { idxs -> idxs.map { this[it] }.joinToString("") }

fun <T> List<T>.combinations(size: Int): List<List<T>> =
    if (size == 0) listOf(emptyList())
    else flatMapIndexed { i, item -> drop(i + 1).combinations(size - 1).map { listOf(item) + it } }

fun main() {
    val solution =
        solution(
            arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"),
            intArrayOf(2, 3, 4),
        )

    println(solution.toList())
}
