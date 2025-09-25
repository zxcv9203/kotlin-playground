package programmers.level1.l142086

fun solution(s: String): IntArray {
    val answer = mutableListOf<Int>()
    val map: MutableMap<Char, Int> = mutableMapOf()
    s.forEachIndexed { index, c ->
        if (map.containsKey(c)) {
            answer.add(index - map[c]!!)
        } else {
            answer.add(-1)
        }
        map[c] = index
    }
    return answer.toIntArray()
}
