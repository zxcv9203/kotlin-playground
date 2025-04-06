package org.example.functional.chpater12.ex02

class Foo {
    fun foo() {
        print("Foo")
    }
}

context(Foo)
fun callFoo() {
    foo()
}

fun main() {
    with(Foo()) {
        callFoo()
    }
}