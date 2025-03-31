package org.example.functional.chapter10.ex04


class Dialog {
    var title: String = ""
    var message: String = ""
    var okButton: Button? = null
    var cancelButton: Button? = null

    fun show() {
        // ...
    }

    class Button {
        var message: String = ""
        var handler: () -> Unit = {}
    }
}

fun showDialog(init: Dialog.() -> Unit): Dialog {
    val dialog = Dialog()
    init.invoke(dialog)
    return dialog
}

fun main() {
    showDialog {
        title = "제목"
        message = "메시지"
        okButton = Dialog.Button()
        okButton?.message = "OK"
        okButton?.handler = { println("OK 버튼 클릭") }
        cancelButton = Dialog.Button()
        cancelButton?.message = "Cancel"
        cancelButton?.handler = { println("Cancel 버튼 클릭") }
    }
}