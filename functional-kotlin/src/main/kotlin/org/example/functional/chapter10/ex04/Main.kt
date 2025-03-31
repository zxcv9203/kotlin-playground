package org.example.functional.chapter10.ex04


class Dialog {
    var title: String = ""
    var message: String = ""
    var okButton: Button? = null
    var cancelButton: Button? = null

    fun show() {
        // ...
    }

    fun okButton(init: Button.() -> Unit) {
        okButton = Button().apply(init)
    }

    fun cancelButton(init: Button.() -> Unit) {
        cancelButton = Button().apply(init)
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
        okButton {
            message = "확인"
            handler = { println("확인 버튼 클릭") }
        }
        cancelButton {
            message = "취소"
            handler = { println("취소 버튼 클릭") }
        }
    }
}