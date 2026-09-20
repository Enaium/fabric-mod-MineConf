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

package cn.enaium.mineconf.kt

import cn.enaium.mineconf.core.utility.SystemFontFinder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS
import java.io.File

/**
 * @author Enaium
 */
class FontTest {

    /**
     * A text of the language of the system, a font that is picked has to contain every character of it.
     */
    private val chinese = "中发门见"

    @Test
    fun notFound() {
        assertTrue(SystemFontFinder.findFontFiles("mineconf-no-such-font").isEmpty())
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    fun windows() {
        val font = SystemFontFinder.findFontFiles("msyh").firstOrNull {
            SystemFontFinder.supports(it, chinese)
        }
        assertNotNull(font, "no chinese font of Windows was found")
        assertEquals("C:\\Windows\\Fonts\\msyh.ttc", font!!.file.absolutePath)
    }

    @Test
    @EnabledOnOs(OS.MAC)
    fun macOS() {
        // most of the chinese fonts of macOS are in the supplemental directory
        assertTrue(
            SystemFontFinder.getFontDirs().contains(File("/System/Library/Fonts/Supplemental")),
            "the supplemental directory of macOS is not searched"
        )

        val font = listOf("PingFang SC", "PingFang", "Hiragino Sans GB", "STHeiti", "Songti SC", "Songti", "Arial Unicode")
            .firstNotNullOfOrNull { name ->
                SystemFontFinder.findFontFiles(name).firstOrNull { SystemFontFinder.supports(it, chinese) }
            }
        assertNotNull(font, "no chinese font of macOS was found")
        println("macOS chinese font: ${font!!.file} (face ${font.faceIndex}, ${font.family})")

        assertTrue(font.faceIndex >= 0)
        assertNotNull(font.family, "the face of the font collection was not read")
    }

    @Test
    @EnabledOnOs(OS.MAC)
    fun macOSFace() {
        // a font collection has several faces, the face of the requested font has to be picked
        val fonts = SystemFontFinder.findFontFiles("Songti")
        assertTrue(fonts.isNotEmpty(), "no Songti font of macOS was found")
        fonts.forEach {
            assertNotNull(it.family, "the face of ${it.file} was not read")
            assertTrue(
                it.family!!.contains("songti", ignoreCase = true),
                "the face ${it.family} of ${it.file} does not belong to Songti"
            )
        }
    }

    @Test
    @EnabledOnOs(OS.MAC)
    fun macOSIsSorted() {
        // the same font has to be picked on every start, the order of the directories must not matter
        assertEquals(SystemFontFinder.findFontFiles("Songti"), SystemFontFinder.findFontFiles("Songti"))
    }
}
