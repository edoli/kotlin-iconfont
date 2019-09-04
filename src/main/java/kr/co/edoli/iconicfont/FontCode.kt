package kr.co.edoli.iconicfont

/**
 * Created by daniel on 16. 2. 29.
 */
data class FontCode(
        val name: String,
        val code: String
) {

    constructor(name: String, code: Char) : this(name, unicodeEscaped(code))

    companion object {
        fun unicodeEscaped(ch: Char): String {
            return when {
                ch.toInt() < 0x10 -> "000" + Integer.toHexString(ch.toInt())
                ch.toInt() < 0x100 -> "00" + Integer.toHexString(ch.toInt())
                ch.toInt() < 0x1000 -> "0" + Integer.toHexString(ch.toInt())
                else -> Integer.toHexString(ch.toInt())
            }
        }
    }
}

