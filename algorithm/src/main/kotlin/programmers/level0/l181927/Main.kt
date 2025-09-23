package programmers.level0.l181927

fun solution(num_list: IntArray): IntArray {
    val last = num_list.last()
    val secondLast = num_list[num_list.lastIndex - 1]

    val addNumber = if (last > secondLast) last - secondLast else last * 2

    return num_list + addNumber
}
