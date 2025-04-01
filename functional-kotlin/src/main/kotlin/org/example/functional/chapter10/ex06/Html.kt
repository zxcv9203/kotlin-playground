package org.example.functional.chapter10.ex06

@DslMarker
annotation class HtmlDsl

@HtmlDsl
fun html(init: HtmlBuilder.() -> Unit): HtmlBuilder {
    return HtmlBuilder().apply(init)
}

@HtmlDsl
class HtmlBuilder {
    private var head: HeadBuilder? = null
    private var body: BodyBuilder? = null

    @HtmlDsl
    fun head(init: HeadBuilder.() -> Unit) {
        head = HeadBuilder().apply(init)
    }

    @HtmlDsl
    fun body(init: BodyBuilder.() -> Unit) {
        body = BodyBuilder().apply(init)
    }

    override fun toString(): String =
        listOfNotNull(head, body)
            .joinToString(
                separator = "",
                prefix = "<html>\n",
                postfix = "</html>",
                transform = { "$it\n" },
            )
}

@HtmlDsl
class HeadBuilder {
    var title: String = ""
    private var cssList: List<String> = emptyList()

    @HtmlDsl
    fun css(body: String) {
        cssList += body
    }

    override fun toString(): String {
        val css = cssList.joinToString(separator = "") {
            "<style>$it</style>"
        }
        return "<head>\n<title>$title</title>\n$css</head>\n"
    }
}

@HtmlDsl
class BodyBuilder {
    private var elements: List<BodyElement> = emptyList()

    @HtmlDsl
    fun h1(text: String) {
        elements += H1(text)
    }

    @HtmlDsl
    fun h3(text: String) {
        elements += H3(text)
    }

    operator fun String.unaryPlus() {
        elements += Text(this)
    }

    override fun toString(): String {
        val body = elements.joinToString(separator = "\n")
        return "<body>\n$body\n</body>"
    }
}

sealed interface BodyElement
data class H1(val text: String) : BodyElement {
    override fun toString(): String = "<h1>$text</h1>"
}

data class H3(val text: String) : BodyElement {
    override fun toString(): String = "<h3>$text</h3>"
}

data class Text(val text: String) : BodyElement {
    override fun toString(): String = text
}

val html = html {
    head {
        title = "My WebSite"
        css("Some CSS1")
        css("Some CSS2")
    }
    body {
        h1("Hello World")
        h3("This is a subtitle")
        +"This is a text"
    }
}

fun main() {
    println(html)
}