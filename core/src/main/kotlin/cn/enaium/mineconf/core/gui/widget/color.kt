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

package cn.enaium.mineconf.core.gui.widget

import cn.enaium.mineconf.core.conf.Vec3Conf
import cn.enaium.mineconf.core.conf.Vec4Conf
import cn.enaium.mineconf.core.conf.Widget
import cn.enaium.mineconf.core.type.Vec3
import cn.enaium.mineconf.core.type.Vec4
import imgui.ImGui
import imgui.ImVec2
import imgui.flag.ImGuiColorEditFlags

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun Vec3Conf<*>.color3(id: String) {
    when (value.x) {
        is Long, is Int, is Short, is Byte -> {
            val col = floatArrayOf(
                (value.x.toDouble() / 255.0).toFloat(),
                (value.y.toDouble() / 255.0).toFloat(),
                (value.z.toDouble() / 255.0).toFloat(),
            )
            when (widget) {
                Widget.COLOR_EDIT -> {
                    ImGui.setNextItemWidth(-Float.MIN_VALUE)
                    ImGui.colorEdit3(id, col)
                }

                Widget.COLOR_PICKER -> {
                    ImGui.setNextItemWidth(ImGui.getContentRegionAvail().x / 3f)
                    colorPicker3(id, col)
                }

                else -> throw UnsupportedOperationException("Unsupported widget type: $widget")
            }
            val red = col[0] * 255.0
            val green = col[1] * 255.0
            val blue = col[2] * 255.0
            when (value.x) {
                is Long -> {
                    this as Vec3Conf<Long>
                    this.value = Vec3(red.toLong(), green.toLong(), blue.toLong())
                }

                is Int -> {
                    this as Vec3Conf<Int>
                    this.value = Vec3(red.toInt(), green.toInt(), blue.toInt())
                }

                is Short -> {
                    this as Vec3Conf<Short>
                    this.value = Vec3(red.toInt().toShort(), green.toInt().toShort(), blue.toInt().toShort())
                }

                is Byte -> {
                    this as Vec3Conf<Byte>
                    this.value = Vec3(red.toInt().toByte(), green.toInt().toByte(), blue.toInt().toByte())
                }
            }
        }

        is Double, Float -> {
            val col = floatArrayOf(
                this.value.x.toFloat(),
                this.value.y.toFloat(),
                this.value.z.toFloat(),
            )
            ImGui.setNextItemWidth(-Float.MIN_VALUE)
            when (widget) {
                Widget.COLOR_EDIT -> {
                    ImGui.colorEdit3(id, col)
                }

                Widget.COLOR_PICKER -> {
                    colorPicker3(id, col)
                }

                else -> throw UnsupportedOperationException("Unsupported widget type: $widget")
            }

            when (value.x) {
                is Double -> {
                    this as Vec3Conf<Double>
                    this.value = Vec3(col[0].toDouble(), col[1].toDouble(), col[2].toDouble())
                }

                is Float -> {
                    this as Vec3Conf<Float>
                    this.value = Vec3(col[0], col[1], col[2])
                }
            }
        }
    }
}

fun colorPicker3(id: String, col: FloatArray) {
    ImGui.beginGroup()
    ImGui.setNextItemWidth(ImGui.getContentRegionAvail().x / 3f)
    ImGui.colorPicker3(
        "${id}_picker",
        col,
        ImGuiColorEditFlags.NoInputs or ImGuiColorEditFlags.NoSidePreview
    )
    ImGui.sameLine()
    ImGui.beginGroup()
    ImGui.colorEdit3(
        "${id}_rgb",
        col,
        ImGuiColorEditFlags.DisplayRGB or ImGuiColorEditFlags.NoSmallPreview
    )
    ImGui.colorEdit3(
        "${id}_hsv",
        col,
        ImGuiColorEditFlags.DisplayHSV or ImGuiColorEditFlags.NoSmallPreview
    )
    ImGui.colorEdit3(
        "${id}_hex",
        col,
        ImGuiColorEditFlags.DisplayHex or ImGuiColorEditFlags.NoSmallPreview
    )
    ImGui.setNextItemWidth(200f)
    ImGui.getStyle().framePadding = ImVec2(20f, 20f)
    ImGui.colorEdit3(
        "${id}_preview",
        col,
        ImGuiColorEditFlags.NoInputs or ImGuiColorEditFlags.NoInputs or ImGuiColorEditFlags.NoPicker
    )
    ImGui.getStyle().framePadding = ImVec2(1f, 1f)
    ImGui.endGroup()
    ImGui.endGroup()
}

@Suppress("UNCHECKED_CAST")
fun Vec4Conf<*>.color4(id: String) {
    when (value.x) {
        is Long, is Int, is Short, is Byte -> {
            val col = floatArrayOf(
                (value.x.toDouble() / 255.0).toFloat(),
                (value.y.toDouble() / 255.0).toFloat(),
                (value.z.toDouble() / 255.0).toFloat(),
                (value.w.toDouble() / 255.0).toFloat(),
            )
            ImGui.setNextItemWidth(-Float.MIN_VALUE)
            when (widget) {
                Widget.COLOR_EDIT -> {
                    ImGui.colorEdit4(id, col, ImGuiColorEditFlags.AlphaBar)
                }

                Widget.COLOR_PICKER -> {
                    colorPicker4(id, col)
                }

                else -> throw UnsupportedOperationException("Unsupported widget type: $widget")
            }
            val red = col[0] * 255f
            val green = col[1] * 255f
            val blue = col[2] * 255f
            val alpha = col[3] * 255f
            when (value.x) {
                is Long -> {
                    this as Vec4Conf<Long>
                    this.value = Vec4(red.toLong(), green.toLong(), blue.toLong(), alpha.toLong())
                }

                is Int -> {
                    this as Vec4Conf<Int>
                    this.value = Vec4(red.toInt(), green.toInt(), blue.toInt(), alpha.toInt())
                }

                is Short -> {
                    this as Vec4Conf<Short>
                    this.value = Vec4(
                        red.toInt().toShort(),
                        green.toInt().toShort(),
                        blue.toInt().toShort(),
                        alpha.toInt().toShort()
                    )
                }

                is Byte -> {
                    this as Vec4Conf<Byte>
                    this.value = Vec4(
                        red.toInt().toByte(),
                        green.toInt().toByte(),
                        blue.toInt().toByte(),
                        alpha.toInt().toByte()
                    )
                }
            }
        }

        is Double, Float -> {
            val col = floatArrayOf(
                this.value.x.toFloat(),
                this.value.y.toFloat(),
                this.value.z.toFloat(),
                this.value.w.toFloat()
            )
            when (widget) {
                Widget.COLOR_EDIT -> {
                    ImGui.setNextItemWidth(-Float.MIN_VALUE)
                    ImGui.colorEdit4(id, col)
                }

                Widget.COLOR_PICKER -> {
                    colorPicker4(id, col)
                }

                else -> throw UnsupportedOperationException("Unsupported widget type: $widget")
            }

            when (value.x) {
                is Double -> {
                    this as Vec4Conf<Double>
                    this.value = Vec4(col[0].toDouble(), col[1].toDouble(), col[2].toDouble(), col[3].toDouble())
                }

                is Float -> {
                    this as Vec4Conf<Float>
                    this.value = Vec4(col[0], col[1], col[2], col[3])
                }
            }
        }
    }
}

fun colorPicker4(id: String, col: FloatArray) {
    ImGui.beginGroup()
    ImGui.setNextItemWidth(ImGui.getContentRegionAvail().x / 3f)
    ImGui.colorPicker4(
        id,
        col,
        ImGuiColorEditFlags.AlphaBar or ImGuiColorEditFlags.AlphaPreviewHalf or ImGuiColorEditFlags.NoInputs or ImGuiColorEditFlags.NoSidePreview
    )
    ImGui.sameLine()
    ImGui.beginGroup()
    ImGui.colorEdit4(
        "${id}_rgb",
        col,
        ImGuiColorEditFlags.DisplayRGB or ImGuiColorEditFlags.NoSmallPreview
    )
    ImGui.colorEdit4(
        "${id}_hsv",
        col,
        ImGuiColorEditFlags.DisplayHSV or ImGuiColorEditFlags.NoSmallPreview
    )
    ImGui.colorEdit4(
        "${id}_hex",
        col,
        ImGuiColorEditFlags.DisplayHex or ImGuiColorEditFlags.NoSmallPreview
    )
    ImGui.setNextItemWidth(200f)
    ImGui.getStyle().framePadding = ImVec2(20f, 20f)
    ImGui.colorEdit4(
        "${id}_preview",
        col,
        ImGuiColorEditFlags.AlphaPreviewHalf or ImGuiColorEditFlags.NoInputs or ImGuiColorEditFlags.NoPicker
    )
    ImGui.getStyle().framePadding = ImVec2(1f, 1f)
    ImGui.endGroup()
    ImGui.endGroup()
}