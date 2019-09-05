package kr.co.edoli.iconicfont



val dashToCamel = { cn: String ->
    val blocks = cn.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    val builder = StringBuilder()

    for (block in blocks) {
        val camelBlock = Character.toUpperCase(block.get(0)) + block.substring(1)
        builder.append(camelBlock)
    }

    val text = builder.toString()
    text[0].toLowerCase() + text.substring(1)
}

val underscoreToCamel = { cn: String ->
    val blocks = cn.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    val builder = StringBuilder()

    for (block in blocks) {
        val camelBlock = Character.toUpperCase(block.get(0)) + block.substring(1)
        builder.append(camelBlock)
    }

    val text = builder.toString()
    text[0].toLowerCase() + text.substring(1)
}

fun generateCodeFile(name: String, fontCodes: List<FontCode>): String {
    val code = StringBuilder()

    code.append("object $name {\n")
    for (fontCode in fontCodes) {
        var codeName = dashToCamel(underscoreToCamel(fontCode.name))
        if (codeName[0].isDigit()) {
            codeName = "a$codeName"
        }
        val line = String.format("    val %s = \"\\u%s\"\n", codeName, fontCode.code)
        code.append(line)
    }
    code.append("}")

    return code.toString()
}

