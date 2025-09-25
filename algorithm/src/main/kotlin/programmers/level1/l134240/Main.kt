package programmers.level1.l134240

fun solution(food: IntArray): String {
    var answer = "0"
    for (i in food.indices.reversed()) {
        repeat(food[i] / 2) {
            answer = "$i$answer$i"
        }
    }
    return answer
}
