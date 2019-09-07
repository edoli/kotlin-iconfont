package kr.co.edoli.iconicfont.parser

// https://github.com/WordPress/dashicons
object DashiconsParser : CssParser() {
    override val name = "Dashicons"
    override val cssPath = "fonts/dashicons.css"
}
