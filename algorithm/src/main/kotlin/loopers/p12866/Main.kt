package loopers.p12866

private const val MOD = 1_000_000_007L

fun main() {
    val n = readln().toInt()
    val human = readln()

    val count = LongArray(4)

    for (base in human) {
        when (base) {
            'A' -> count[0]++
            'C' -> count[1]++
            'G' -> count[2]++
            'T' -> count[3]++
        }
    }

    val answer = count[0] % MOD * (count[1] % MOD) % MOD * (count[2] % MOD) % MOD * (count[3] % MOD) % MOD
    println(answer)
}
