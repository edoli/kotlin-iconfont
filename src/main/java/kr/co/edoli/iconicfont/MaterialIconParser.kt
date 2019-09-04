package kr.co.edoli.iconicfont

import org.apache.commons.io.FileUtils

import java.io.File
import java.io.IOException
import java.util.ArrayList

/**
 * Created by daniel on 16. 2. 29.
 */
object MaterialIconParser {

    fun parseMaterialIcon() {
        val codePath = "fonts/material-design-icons/iconfont/codepoints"

        try {
            val lines = FileUtils.readLines(File(codePath))

            val fontCodes = ArrayList<FontCode>()

            for (line in lines) {
                val splitted = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                fontCodes.add(FontCode(splitted[0], splitted[1]))
            }

            FileUtils.write(File("generated/MaterialIcon.java"), generateCodeFile("MaterialIcon", fontCodes, underscoreToCamel))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
