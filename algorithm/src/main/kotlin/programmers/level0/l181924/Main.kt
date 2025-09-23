package programmers.level0.l181924

fun solution(
    arr: IntArray,
    queries: Array<IntArray>,
): IntArray {
    queries.forEach {
        val temp = arr[it[0]]
        arr[it[0]] = arr[it[1]]
        arr[it[1]] = temp
    }
    return arr
}
