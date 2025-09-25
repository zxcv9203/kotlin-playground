package programmers.level1.l131705

fun solution(number: IntArray): Int =
    number.indices
        .toList()
        .combinations(3)
        .count { (i, j, k) -> number[i] + number[j] + number[k] == 0 }

private fun <T> List<T>.combinations(r: Int): List<List<T>> {
    if (r == 0) return listOf(emptyList())
    if (r > size) return emptyList()

    val result = mutableListOf<List<T>>()

    fun backtrack(
        start: Int,
        current: MutableList<T>,
    ) {
        if (current.size == r) {
            result.add(current.toList())
            return
        }

        for (i in start until size) {
            current.add(this[i])
            backtrack(i + 1, current)
            current.removeAt(current.lastIndex)
        }
    }

    backtrack(0, mutableListOf())
    return result
}
