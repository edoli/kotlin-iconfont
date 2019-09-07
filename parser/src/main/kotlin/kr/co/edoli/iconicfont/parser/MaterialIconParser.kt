package kr.co.edoli.iconicfont.parser

import kr.co.edoli.iconicfont.FontCode
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.util.*

// https://material.io/resources/icons/?style=baseline
object MaterialIconParser : BaseParser() {
    override val name = "MaterialIcon"

    override fun parse(): List<FontCode> {
        val codePath = "fonts/material-design-icons/iconfont/codepoints"

        try {
            val lines = FileUtils.readLines(File(codePath), "UTF-8")

            val fontCodes = ArrayList<FontCode>()

            for (line in lines) {
                val splitted = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                fontCodes.add(FontCode(splitted[0], splitted[1]))
            }

            return fontCodes
        } catch (e: IOException) {
            e.printStackTrace()
            return listOf()
        }

    }
}
