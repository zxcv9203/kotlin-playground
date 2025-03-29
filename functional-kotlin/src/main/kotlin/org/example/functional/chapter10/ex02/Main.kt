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

fun main() {
    val dialog = Dialog()
    dialog.title = "제목"
    dialog.message = "메시지"
    dialog.okButtonText = "확인"
    dialog.okButtonHandler = { println("확인 버튼 클릭") }
    dialog.cancelButtonText = "취소"
    dialog.cancelButtonHandler = { println("취소 버튼 클릭") }
    dialog.show()
}