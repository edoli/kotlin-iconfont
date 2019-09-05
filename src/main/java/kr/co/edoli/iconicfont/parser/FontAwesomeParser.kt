package kr.co.edoli.iconicfont.parser

// https://fontawesome.com/
object FontAwesomeParser : CssParser() {
    override val name = "FontAwesome"
    override val cssPath = "fonts/fontawesome.css"
}
