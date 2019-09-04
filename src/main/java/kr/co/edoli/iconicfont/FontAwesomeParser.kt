package kr.co.edoli.iconicfont

import com.google.re2j.Pattern
import com.steadystate.css.parser.CSSOMParser
import com.steadystate.css.parser.SACParserCSS3
import org.apache.commons.io.FileUtils
import org.w3c.css.sac.InputSource
import org.w3c.dom.css.CSSStyleRule

import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.ArrayList

/**
 * Created by daniel on 16. 2. 29.
 */
object FontAwesomeParser {

    fun parseFontAwesome() {
        val cssPath = "fonts/fontawesome.css"

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

                    val start = css.indexOf("\"")
                    val end = css.indexOf("\"", start + 1)

                    val code = css.substring(start + 1, end)[0]

                    while (sm.find()) {
                        val name = sm.group(1)

                        fontCodes.add(FontCode(name, code))
                    }
                }
            }

            FileUtils.write(File("generated/FontAwesomes.java"), generateCodeFile("FontAwesomes", fontCodes, dashToCamel))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
