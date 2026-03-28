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
import imgui.ImFontConfig
import imgui.ImFontGlyphRangesBuilder
import imgui.ImGui
import java.util.*

/**
 * @author Enaium
 */
object MineConfGui {
    fun initFonts() {
        val rangesBuilder = ImFontGlyphRangesBuilder()
        val io = ImGui.getIO()
        rangesBuilder.addRanges(io.fonts.glyphRangesDefault)
        rangesBuilder.addRanges(io.fonts.glyphRangesGreek)
        rangesBuilder.addRanges(io.fonts.glyphRangesKorean)
        rangesBuilder.addRanges(io.fonts.glyphRangesJapanese)
        rangesBuilder.addRanges(io.fonts.glyphRangesChineseFull)
        rangesBuilder.addRanges(io.fonts.glyphRangesChineseSimplifiedCommon)
        rangesBuilder.addRanges(io.fonts.glyphRangesCyrillic)
        rangesBuilder.addRanges(io.fonts.glyphRangesThai)
        rangesBuilder.addRanges(io.fonts.glyphRangesVietnamese)
        val fontConfig = ImFontConfig()
        fontConfig.mergeMode = true
        io.fonts.clear()
        val glyphRanges = rangesBuilder.buildRanges()
        val sizePixels = 16f * MineConfConfig.fontScale.value
        io.fonts.addFontFromMemoryTTF(
            MineConf::class.java.getResource("/Minecraft.ttf")!!.readBytes(),
            sizePixels,
            glyphRanges
        )

        getPreferredFonts(Locale.getDefault()).firstNotNullOfOrNull { SystemFontFinder.findFontFile(it) }?.also {
            io.fonts.addFontFromFileTTF(
                it.absolutePath,
                sizePixels,
                fontConfig,
                glyphRanges
            )
        }

        io.fonts.build()
    }

    fun getPreferredFonts(locale: Locale): List<String> {
        return when (locale.language) {
            "zh" -> listOf(
                "msyh",
                "simsun",
                "sourcehan",
            )

            "ja" -> listOf(
                "sourcehan"
            )

            "ko" -> listOf(
                "sourcehan"
            )

            else -> listOf(
                "segoeui",
            )
        }
    }
}
