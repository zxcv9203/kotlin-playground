package programmers.level0.lessons181936

fun solution(
    number: Int,
    n: Int,
    m: Int,
): Int = if (number % n == 0 && number % m == 0) 1 else 0
