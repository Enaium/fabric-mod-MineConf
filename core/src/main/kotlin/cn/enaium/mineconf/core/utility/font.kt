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

    private val cache = mutableMapOf<String, File?>()

    fun findFontFile(fontName: String): File? {
        cache[fontName]?.let { return it }

        val dirs = getFontDirs()

        for (dir in dirs) {
            val result = scanDir(dir, fontName)
            if (result != null) {
                cache[fontName] = result
                return result
            }
        }

        cache[fontName] = null
        return null
    }

    private fun scanDir(dir: File, targetName: String): File? {
        if (!dir.exists()) return null

        dir.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                val result = scanDir(file, targetName)
                if (result != null) return result
            } else if (file.extension.lowercase() in listOf("ttf", "ttc", "otf")) {
                try {
                    val inputStream = file.inputStream()
                    if (isMatch(file.name, targetName)) {
                        inputStream.close()
                        return file
                    }
                    inputStream.close()
                } catch (_: Exception) {
                }
            }
        }
        return null
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

    private fun getFontDirs(): List<File> {
        val os = System.getProperty("os.name").lowercase()
        val home = System.getProperty("user.home")

        return when {
            os.contains("win") -> listOf(
                File("C:\\Windows\\Fonts")
            )

            os.contains("mac") -> listOf(
                File("/System/Library/Fonts"),
                File("/Library/Fonts"),
                File("$home/Library/Fonts")
            )

            else -> listOf(
                File("/usr/share/fonts"),
                File("/usr/local/share/fonts"),
                File("$home/.fonts"),
                File("$home/.local/share/fonts")
            )
        }
    }
}