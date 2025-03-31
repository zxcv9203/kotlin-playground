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

fun makeButton(init: Dialog.Button.() -> Unit): Dialog.Button {
    return Dialog.Button().apply(init)
}

fun main() {
    showDialog {
        title = "제목"
        message = "메시지"
        okButton = makeButton {
            message = "확인"
            handler = { println("확인 버튼 클릭") }
        }
        cancelButton = makeButton {
            message = "취소"
            handler = { println("취소 버튼 클릭") }
        }
    }
}