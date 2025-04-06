package org.example.functional.chpater12.ex03

class Foo {
    fun foo() {
        print("Foo")
    }
}

class Boo {
    fun boo() {
        print("Boo")
    }
}

context(Foo, Boo)
fun callFooBoo() {
    foo()
    boo()
}

context(Foo, Boo)
fun callFooBoo2() {
    callFooBoo()
}

fun main() {
    with(Foo()) {
        with(Boo()) {
            callFooBoo()
            callFooBoo2()
        }
    }
    with(Boo()) {
        with(Foo()) {
            callFooBoo()
            callFooBoo2()
        }
    }
}