package kr.co.edoli.iconicfont.parser

import com.google.re2j.Pattern
import com.steadystate.css.parser.CSSOMParser
import com.steadystate.css.parser.SACParserCSS3
import kr.co.edoli.iconicfont.FontCode
import kr.co.edoli.iconicfont.dashToCamel
import kr.co.edoli.iconicfont.generateCodeFile
import org.apache.commons.io.FileUtils
import org.w3c.css.sac.InputSource
import org.w3c.dom.css.CSSStyleRule
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.*

abstract class CssParser : BaseParser() {
    abstract val name: String
    abstract val cssPath: String

    override fun parse() {
        val sp = Pattern.compile("\\.(\\S+):before")

        try {

            val fontCodes = ArrayList<FontCode>()

            val source = InputSource(FileReader(cssPath))

            val parser = CSSOMParser(SACParserCSS3())
            val sheet = parser.parseStyleSheet(source, null, null)


            for (i in 0 until sheet.cssRules.length) {
                val rule = sheet.cssRules.item(i)

                if (rule is CSSStyleRule) {
                    if ("before" !in rule.selectorText) {
                        continue
                    }
                    val sm = sp.matcher(rule.selectorText)


                    val css = rule.style.cssText

                    if (!css.contains("content") || !css.contains("\"")) {
                        continue
                    }

                    val start = css.indexOf("\"")
                    val end = css.lastIndexOf("\"")

                    val code = css.substring(start + 1, end)[0]

                    while (sm.find()) {
                        val name = sm.group(1)

                        fontCodes.add(FontCode(name, code))
                    }
                }
            }

            FileUtils.write(File("generated/$name.kotlin"), generateCodeFile(name, fontCodes))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}