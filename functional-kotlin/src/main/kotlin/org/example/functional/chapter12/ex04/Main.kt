package org.example.functional.chapter12.ex04

interface Foo {
    fun foo() {
        print("Foo")
    }
}

interface Boo {
    fun boo() {
        print("Boo")
    }
}

context(Foo, Boo)
fun callFooBoo() {
    foo()
    boo()
}

class FooBoo: Foo, Boo {
    fun call() {
        callFooBoo()
    }
}

fun main() {
    val fooBoo = FooBoo()
    fooBoo.call()
}