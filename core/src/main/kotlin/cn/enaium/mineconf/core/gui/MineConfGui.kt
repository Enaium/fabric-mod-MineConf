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
package cn.enaium.mineconf.core.gui

import cn.enaium.mineconf.core.MineConf
import cn.enaium.mineconf.core.config.MineConfConfig
import cn.enaium.mineconf.core.utility.SystemFontFinder
import imgui.ImFont
import imgui.ImFontAtlas
import imgui.ImFontConfig
import imgui.ImFontGlyphRangesBuilder
import imgui.ImGui
import java.nio.file.Files
import java.util.*

/**
 * @author Enaium
 */
object MineConfGui {

    /**
     * The bundled font is written to a temporary file: the memory font of the binding keeps a pointer to a
     * buffer of the byte array that is released after the call, so a merged font would read garbage.
     */
    private val minecraftFont by lazy {
        val path = Files.createTempFile("mineconf-", ".ttf")
        path.toFile().deleteOnExit()
        MineConf::class.java.getResourceAsStream("/Minecraft.ttf")!!.use { input ->
            Files.newOutputStream(path).use { output -> input.copyTo(output) }
        }
        path.toFile()
    }

    /**
     * Font of the MineConf windows, the default font of the ImGui context is left alone, so only the
     * windows of MineConf use it.
     */
    private var font: ImFont? = null

    private var fontSize = 0f

    /**
     * The configurations are kept alive, a temporary one can be collected while ImGui is still reading it.
     */
    private val bundledFontConfig = ImFontConfig()

    private val mergeFontConfig = ImFontConfig()

    fun initFonts() {
        val io = ImGui.getIO()
        val atlas = io.fonts
        if (font != null && atlas.isBuilt()) {
            return
        }

        fontSize = 16f * MineConfConfig.fontScale.value

        val rangesBuilder = ImFontGlyphRangesBuilder()
        rangesBuilder.addRanges(atlas.glyphRangesDefault)
        rangesBuilder.addRanges(atlas.glyphRangesGreek)
        rangesBuilder.addRanges(atlas.glyphRangesKorean)
        rangesBuilder.addRanges(atlas.glyphRangesJapanese)
        rangesBuilder.addRanges(atlas.glyphRangesChineseFull)
        rangesBuilder.addRanges(atlas.glyphRangesChineseSimplifiedCommon)
        rangesBuilder.addRanges(atlas.glyphRangesCyrillic)
        rangesBuilder.addRanges(atlas.glyphRangesThai)
        rangesBuilder.addRanges(atlas.glyphRangesVietnamese)
        val glyphRanges = rangesBuilder.buildRanges()

        // Do not replace the default font of the context, otherwise every other window would use it too. The
        // wrapper is never null, so the pointer has to be checked.
        if (io.fontDefault.ptr == 0L) {
            io.fontDefault = atlas.addFontDefault()
        }

        val bundled = try {
            atlas.addFontFromFileTTF(
                minecraftFont.absolutePath,
                fontSize,
                bundledFontConfig,
                glyphRanges
            )
        } catch (e: Throwable) {
            System.err.println("MineConf: unable to load the bundled font: $e")
            null
        }

        val system = addSystemFont(atlas, glyphRanges, bundled != null)

        font = bundled ?: system

        if (!atlas.build()) {
            System.err.println("MineConf: unable to build the font atlas")
        }
    }

    /**
     * Applies the font of MineConf to the windows drawn until [popFont], other windows are not affected.
     */
    fun pushFont() {
        font?.also {
            ImGui.pushFont(it, fontSize)
        }
    }

    fun popFont() {
        if (font != null) {
            ImGui.popFont()
        }
    }

    /**
     * Merges a font of the system that can render the language of the system, so the bundled font does not
     * have to contain every script. Returns the font of the system when the bundled font is not available.
     */
    private fun addSystemFont(atlas: ImFontAtlas, glyphRanges: ShortArray, merge: Boolean): ImFont? {
        val locale = Locale.getDefault()
        val probe = probeText(locale)

        getSystemFonts(locale).forEach { name ->
            SystemFontFinder.findFontFiles(name).forEach { fontFile ->
                if (!SystemFontFinder.supports(fontFile, probe)) {
                    return@forEach
                }
                try {
                    mergeFontConfig.mergeMode = merge
                    mergeFontConfig.fontNo = fontFile.faceIndex
                    atlas.addFontFromFileTTF(
                        fontFile.file.absolutePath,
                        fontSize,
                        mergeFontConfig,
                        glyphRanges
                    )?.also {
                        println(
                            "MineConf: ${if (merge) "merged" else "added"} the system font " +
                                "${fontFile.file} (face ${fontFile.faceIndex}, ${fontFile.family})"
                        )
                        return it
                    }
                } catch (e: Throwable) {
                    System.err.println("MineConf: unable to load the system font ${fontFile.file}: $e")
                }
            }
        }

        System.err.println("MineConf: no system font for ${locale.language} was found")
        return null
    }

    /**
     * A text of the language of the system, a font has to contain every character of it, otherwise it cannot
     * render the language. Simplified characters are used for Chinese, so a traditional only font is skipped.
     */
    private fun probeText(locale: Locale): String {
        return when (locale.language) {
            "zh" -> "中发门见"
            "ja" -> "あ漢"
            "ko" -> "한글"
            else -> "A"
        }
    }

    private fun getSystemFonts(locale: Locale): List<String> {
        return when (locale.language) {
            "zh" -> listOf(
                "PingFang SC",
                "PingFang",
                "Hiragino Sans GB",
                "STHeiti",
                "Heiti SC",
                "Songti SC",
                "Songti",
                "Arial Unicode",
                "Microsoft YaHei",
                "msyh",
                "SimSun",
                "simsun",
                "SimHei",
                "Noto Sans CJK SC",
                "Noto Sans SC",
                "Source Han Sans",
                "sourcehan"
            )

            "ja" -> listOf(
                "Hiragino Sans",
                "Yu Gothic",
                "Meiryo",
                "Noto Sans CJK JP",
                "Source Han Sans",
                "sourcehan"
            )

            "ko" -> listOf(
                "Apple SD Gothic Neo",
                "Malgun Gothic",
                "Noto Sans CJK KR",
                "Source Han Sans",
                "sourcehan"
            )

            else -> listOf(
                "Segoe UI",
                "Helvetica Neue",
                "Arial"
            )
        }
    }

    fun initStyle() {
        when (MineConfConfig.style.value) {
            MineConfConfig.Style.DEFAULT -> {}
            MineConfConfig.Style.CLASSIC -> ImGui.styleColorsClassic()
            MineConfConfig.Style.LIGHT -> ImGui.styleColorsLight()
            MineConfConfig.Style.DARK -> ImGui.styleColorsDark()
        }
    }
}
