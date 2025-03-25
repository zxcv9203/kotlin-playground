package org.example.functional.chapter9.ex03

fun main() {
    // 시퀀스는 최종 연산을 호출할 때마다 연산을 수행합니다.
    val s = (1..6).asSequence()
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it "); it * 2 }

    s.find { it > 3 }
    println()
    s.find { it > 3 }
    println()
    s.find { it > 3 }
    println()
    // 같은 연산을 이터러블(리스트)에서 수행할 경우
    val i = (1..6).asIterable()
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it "); it * 2 }
    // filter, map은 이미 처리되었으므로 출력 내용이 나오지 않음
    i.find { it > 3 }
    i.find { it > 3 }
    i.find { it > 3 }
}