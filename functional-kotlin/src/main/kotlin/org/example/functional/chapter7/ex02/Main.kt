package org.example.functional.chapter7.ex02

inline fun print(message: Any?) {
    System.out.print(message)
}

fun main() {
    print("A")
    print("B")
    print("C")

    // 내부적으로는 다음과 같이 실행됩니다.
     System.out.print("A")
     System.out.print("B")
     System.out.print("C")
}

