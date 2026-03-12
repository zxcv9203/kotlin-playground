package loopers.p30089

fun String.isPalindrome(): Boolean {
    return this == this.reversed()
}

fun main() {
    val n = readln().toInt()

    repeat(n) {
        val s = readln()
        var idx = 0

        var slice = ""
        while (!(s + slice).isPalindrome()) {
            slice = s.substring(0, ++idx).reversed()
        }

        println(s + slice)
    }
}
