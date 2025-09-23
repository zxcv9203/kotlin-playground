package programmers.level0.l181928

fun solution(num_list: IntArray): Int {
    val odd = StringBuilder()
    val even = StringBuilder()

    num_list.forEach {
        if (it % 2 == 0) even.append(it) else odd.append(it)
    }
    return odd.toString().toInt() + even.toString().toInt()
}
