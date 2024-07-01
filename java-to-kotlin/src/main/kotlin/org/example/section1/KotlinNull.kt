package org.example.section1

fun main() {
    val person = Person(null, "Smith")
    // NPE 발생
//    startsWithA(person.firstName)

    // 어노테이션을 통해 Null이 들어올 수 있다는 것을 알기 때문에 null 체크를 해주어야함 (컴파일 에러 발생)
//    startsWithA(person.lastName)
}

fun startsWithA(str: String): Boolean {
    return str.startsWith("a")
}