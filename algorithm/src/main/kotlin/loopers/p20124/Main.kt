package loopers.p20124

fun main() {
    val n = readln().toInt()
    var answer = ""
    var bigVote = 0
    repeat(n) {
        val (human, vote) = readln().split(" ")

        if (vote.toInt() > bigVote) {
            bigVote = vote.toInt()
            answer = human
        } else {
            if (vote.toInt() == bigVote && human < answer) {
                answer = human
            }
        }
    }
    println(answer)
}
