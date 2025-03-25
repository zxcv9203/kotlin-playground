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

    i.find { it > 3 }
    println()
    i.find { it > 3 } // 이미 소비되었으므로 출력되지 않음
    println()
    i.find { it > 3 }
}