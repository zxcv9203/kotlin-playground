package org.example.functional.chapter10.ex03

class Dialog {
    var title: String = ""
    var message: String = ""
    var okButtonText: String = ""
    var okButtonHandler: () -> Unit = {}
    var cancelButtonText: String = ""
    var cancelButtonHandler: () -> Unit = {}

    fun show() {
        // ...
    }
}

fun main() {
    Dialog().apply {
        title = "Hello World"
        message = "This is a message."
        okButtonText = "OK"
        okButtonHandler = { println("OK clicked") }
        cancelButtonText = "Cancel"
        cancelButtonHandler = { println("Cancel clicked") }
    }.show()
}
