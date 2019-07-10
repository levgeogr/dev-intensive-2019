package ru.skillbranch.devintensive.extensions

/**
 * @param charCount count of symbols to truncate current string
 *
 * @return String
 */
fun String.truncate(size: Int = 16): String {
    val result = this.trim()
    if (result.length <= size)
        return result
    return result.substring(0, size).trimEnd() + "..."
}

/**
 * @return String text without HTML tags
 */
fun String.stripHtml() = this
    .replace(Regex("<[^>]*>"), "")
    .replace(Regex("&amp;|&lt;|&gt;|&quot;|&apos;|&#\\d+;"), "")
    .replace(Regex(" +"), " ")