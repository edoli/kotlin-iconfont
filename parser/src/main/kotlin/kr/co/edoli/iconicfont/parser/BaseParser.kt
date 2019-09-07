package kr.co.edoli.iconicfont.parser

import kr.co.edoli.iconicfont.FontCode
import org.apache.commons.io.FileUtils
import java.io.File

abstract class BaseParser() {
    abstract val name: String

    abstract fun parse(): List<FontCode>
    fun generate() {
        val fontCodes = parse()
        FileUtils.write(
                File("iconfont/src/main/kotlin/kr/co/edoli/iconicfont/code/$name.kt"),
                generateCodeFile(name, fontCodes),
                "UTF-8")
    }
}