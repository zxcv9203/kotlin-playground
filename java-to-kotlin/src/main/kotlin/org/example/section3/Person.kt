package org.example.section3

// 코틀린에서는 추가 생성자보다는 기본 생성자에 기본값을 지정하는 것을 권장합니다.
class Person(
    name: String = "name",
    val age: Int = 1
) {
    // 생성시 실행할 코드 블록
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다.")
        }
    }

    // 추가 생성자가 필요한 경우 클래스 내부에 선언
    constructor(name: String) : this(name, 1) {
        // 추가 생성자도 코드를 추가할 수 있습니다.
        println("부 생성자 호출")
    }

    // custom getter
    val isAdult: Boolean
        get() = age >= 20

    // name대신 field를 사용하여 무한루프가 발생하지 않도록 합니다. (backing field)
    val name: String = name
        get() = field.uppercase()
}