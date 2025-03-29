package org.example.functional.chapter10.ex02

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

fun showDialog(init: Dialog.() -> Unit): Dialog {
    val dialog = Dialog()
    init.invoke(dialog)
    return dialog
}

fun main() {
    val dialog = Dialog()
    dialog.title = "제목"
    dialog.message = "메시지"
    dialog.okButtonText = "확인"
    dialog.okButtonHandler = { println("확인 버튼 클릭") }
    dialog.cancelButtonText = "취소"
    dialog.cancelButtonHandler = { println("취소 버튼 클릭") }
    dialog.show()

    val dialog2 = Dialog()
    val init: Dialog.() -> Unit = {
        title = "제목"
        message = "메시지"
        okButtonText = "확인"
        okButtonHandler = { println("확인 버튼 클릭") }
        cancelButtonText = "취소"
        cancelButtonHandler = { println("취소 버튼 클릭") }
    }
    init.invoke(dialog2)
    dialog2.show()

    showDialog {
        title = "제목"
        message = "메시지"
        okButtonText = "확인"
        okButtonHandler = { println("확인 버튼 클릭") }
        cancelButtonText = "취소"
        cancelButtonHandler = { println("취소 버튼 클릭") }
    }
}