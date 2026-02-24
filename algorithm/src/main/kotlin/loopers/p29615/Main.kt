package loopers.p29615

fun main() {
    val (n, m) = readln().split(" ").map(String::toInt)
    val list = readln().split(" ").map(String::toInt)
    val friends = readln().split(" ").map(String::toInt).toSet()

    list
        .take(m)
        .count { it !in friends }
        .also { println(it) }
}
