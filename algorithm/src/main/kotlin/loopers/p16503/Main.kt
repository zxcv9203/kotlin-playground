package loopers.p16503

enum class Operator(val symbol: Char, val apply: (Long, Long) -> Long) {
    PLUS('+', Long::plus),
    MINUS('-', Long::minus),
    TIMES('*', Long::times),
    DIVIDE('/', Long::div);

    companion object {
        fun from(c: Char) = entries.first { it.symbol == c }
    }
}

class Expression(private val nums: List<Long>, private val ops: List<Operator>) {

    fun results(): List<Long> = solve(0, nums.lastIndex)

    private fun solve(l: Int, r: Int): List<Long> {
        if (l == r) return listOf(nums[l])

        return (l until r).flatMap { i ->
            val lefts = solve(l, i)
            val rights = solve(i + 1, r)
            lefts.flatMap { a -> rights.map { b -> ops[i].apply(a, b) } }
        }
    }

    companion object {
        fun parse(input: String): Expression {
            val tokens = input.split(" ")
            return Expression(
                nums = tokens.filterIndexed { i, _ -> i % 2 == 0 }.map(String::toLong),
                ops = tokens.filterIndexed { i, _ -> i % 2 == 1 }.map { Operator.from(it[0]) }
            )
        }
    }
}

fun main() {
    val results = Expression.parse(readln()).results()
    println(results.min())
    println(results.max())
}