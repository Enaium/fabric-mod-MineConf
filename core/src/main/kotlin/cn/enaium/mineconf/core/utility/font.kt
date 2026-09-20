/*
 * Copyright 2026 Enaium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.enaium.mineconf.core.utility


import java.io.File

/**
 * @author Enaium
 */
object SystemFontFinder {

    /**
     * A font file and the face to use inside it.
     */
    data class FontFile(val file: File, val faceIndex: Int, val family: String?)

    private val extensions = listOf("ttf", "ttc", "otf", "otc")

    private val cache = mutableMapOf<String, List<FontFile>>()

    /**
     * Every font of the system that matches [fontName], in the order of the font directories.
     */
    fun findFontFiles(fontName: String): List<FontFile> {
        cache[fontName]?.let { return it }

        val result = mutableListOf<FontFile>()
        getFontDirs().forEach { collect(it, fontName, result) }
        cache[fontName] = result
        return result
    }

    /**
     * Whether the face really contains every character of [text], so a font that cannot render the text is
     * never used.
     */
    fun supports(fontFile: FontFile, text: String): Boolean {
        return try {
            val data = FontData(fontFile.file.readBytes())
            val offset = data.faceOffset(fontFile.faceIndex)
            text.all { data.hasGlyph(offset, it.code) }
        } catch (_: Throwable) {
            false
        }
    }

    private fun collect(dir: File, targetName: String, result: MutableList<FontFile>) {
        if (!dir.isDirectory) {
            return
        }
        // Sorted, so that the same font is picked on every start.
        dir.listFiles()?.sortedBy { it.name.lowercase() }?.forEach { file ->
            if (file.isDirectory) {
                collect(file, targetName, result)
            } else if (file.extension.lowercase() in extensions && isMatch(file.name, targetName)) {
                face(file, targetName)?.also { result += it }
            }
        }
    }

    /**
     * The face of [file] that matches [targetName], or the first one, several faces share a file in a collection.
     */
    private fun face(file: File, targetName: String): FontFile? {
        return try {
            val data = FontData(file.readBytes())
            val offsets = data.faceOffsets()
            val index = offsets.indices.firstOrNull {
                isMatch(data.faceName(offsets[it]) ?: "", targetName)
            } ?: 0
            FontFile(file, index, data.faceName(offsets[index]))
        } catch (_: Throwable) {
            null
        }
    }

    private fun isMatch(a: String, b: String): Boolean {
        val na = normalize(a)
        val nb = normalize(b)
        return na == nb || na.contains(nb) || nb.contains(na)
    }

    private fun normalize(name: String): String {
        return name.lowercase()
            .replace(" ", "")
            .replace("-", "")
            .replace("_", "")
    }

    internal fun getFontDirs(): List<File> {
        val os = System.getProperty("os.name").lowercase()
        val home = System.getProperty("user.home")

        return when {
            os.contains("win") -> listOf(
                File("C:\\Windows\\Fonts"),
                File(home, "AppData/Local/Microsoft/Windows/Fonts")
            )

            os.contains("mac") -> listOf(
                File("/System/Library/Fonts"),
                File("/System/Library/Fonts/Supplemental"),
                File("/Library/Fonts"),
                File(home, "Library/Fonts")
            )

            else -> listOf(
                File("/usr/share/fonts"),
                File("/usr/local/share/fonts"),
                File(home, ".fonts"),
                File(home, ".local/share/fonts")
            )
        }
    }

    /**
     * Reads the tables of a font file, both single fonts and collections are supported.
     */
    private class FontData(private val data: ByteArray) {

        fun faceOffsets(): IntArray {
            if (data.size < 12 || tag(0) != "ttcf") {
                return intArrayOf(0)
            }
            val count = u32(8).coerceIn(1, 64)
            return IntArray(count) { u32(12 + it * 4) }.filter { it in 0 until data.size }.toIntArray()
        }

        fun faceOffset(index: Int): Int {
            val offsets = faceOffsets()
            return offsets.getOrElse(index) { offsets.firstOrNull() ?: 0 }
        }

        fun faceName(offset: Int): String? {
            val name = table(offset, "name") ?: return null
            val count = u16(name + 2)
            val storage = name + u16(name + 4)
            var family: String? = null
            for (i in 0 until count) {
                val record = name + 6 + i * 12
                val platform = u16(record)
                val nameId = u16(record + 6)
                val text = readName(storage + u16(record + 10), u16(record + 8), platform) ?: continue
                when (nameId) {
                    6 -> return text
                    1 -> family = family ?: text
                }
            }
            return family
        }

        fun hasGlyph(offset: Int, codepoint: Int): Boolean {
            val cmap = table(offset, "cmap") ?: return false
            val count = u16(cmap + 2)
            for (i in 0 until count) {
                val subtable = cmap + u32(cmap + 4 + i * 8 + 4)
                when (u16(subtable)) {
                    4 -> if (hasGlyphFormat4(subtable, codepoint)) return true
                    12 -> if (hasGlyphFormat12(subtable, codepoint)) return true
                }
            }
            return false
        }

        private fun hasGlyphFormat4(subtable: Int, codepoint: Int): Boolean {
            if (codepoint > 0xFFFF) {
                return false
            }
            val segments = u16(subtable + 6) / 2
            val end = subtable + 14
            val start = end + segments * 2 + 2
            val delta = start + segments * 2
            val range = delta + segments * 2
            for (i in 0 until segments) {
                if (codepoint > u16(end + i * 2) || codepoint < u16(start + i * 2)) {
                    continue
                }
                val idDelta = u16(delta + i * 2)
                val idRangeOffset = u16(range + i * 2)
                if (idRangeOffset == 0) {
                    return (codepoint + idDelta) and 0xFFFF != 0
                }
                val glyph = u16(range + i * 2 + idRangeOffset + (codepoint - u16(start + i * 2)) * 2)
                return glyph != 0 && (glyph + idDelta) and 0xFFFF != 0
            }
            return false
        }

        private fun hasGlyphFormat12(subtable: Int, codepoint: Int): Boolean {
            val groups = u32(subtable + 12)
            var low = 0
            var high = groups - 1
            while (low <= high) {
                val middle = (low + high) / 2
                val group = subtable + 16 + middle * 12
                when {
                    codepoint < u32(group) -> high = middle - 1
                    codepoint > u32(group + 4) -> low = middle + 1
                    else -> return u32(group + 8) + (codepoint - u32(group)) != 0
                }
            }
            return false
        }

        private fun table(offset: Int, tag: String): Int? {
            val tables = u16(offset + 4)
            for (i in 0 until tables) {
                val record = offset + 12 + i * 16
                if (tag(record) == tag) {
                    return u32(record + 8)
                }
            }
            return null
        }

        private fun readName(offset: Int, length: Int, platform: Int): String? {
            if (offset < 0 || length <= 0 || offset + length > data.size) {
                return null
            }
            val text = if (platform == 1) {
                String(data, offset, length, Charsets.ISO_8859_1)
            } else {
                String(data, offset, length, Charsets.UTF_16BE)
            }
            return text.replace("\u0000", "").trim().ifEmpty { null }
        }

        private fun tag(offset: Int): String {
            return String(data, offset, 4, Charsets.ISO_8859_1)
        }

        private fun u16(offset: Int): Int {
            if (offset < 0 || offset + 2 > data.size) {
                return 0
            }
            return ((data[offset].toInt() and 0xFF) shl 8) or (data[offset + 1].toInt() and 0xFF)
        }

        private fun u32(offset: Int): Int {
            if (offset < 0 || offset + 4 > data.size) {
                return 0
            }
            return (u16(offset) shl 16) or u16(offset + 2)
        }
    }
}
