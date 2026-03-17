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

package cn.enaium.mineconf.gui.pane

import cn.enaium.mineconf.MineConf
import cn.enaium.mineconf.conf.Conf
import cn.enaium.mineconf.conf.NumberConf
import cn.enaium.mineconf.conf.OptionConf
import cn.enaium.mineconf.conf.Widget
import imgui.ImGui
import imgui.ImVec2
import imgui.flag.ImGuiSelectableFlags
import imgui.flag.ImGuiTableColumnFlags
import imgui.type.*

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
object MineConfPane {
    fun mineConf(mineConf: MineConf) {
        ImGui.beginTable(mineConf.id, 2)
        ImGui.tableSetupColumn("Name", ImGuiTableColumnFlags.WidthFixed, 0f)
        ImGui.tableSetupColumn("Widget", ImGuiTableColumnFlags.WidthStretch, 1f)
        mineConf.confMap.forEach { (id, conf) ->
            ImGui.tableNextRow()
            ImGui.tableNextColumn()
            name(conf)
            ImGui.tableNextColumn()
            widget(conf as Conf<Any>)
        }
        ImGui.endTable()
    }

    fun name(conf: Conf<*>) {
        ImGui.selectable(conf.name, false, ImGuiSelectableFlags.AllowItemOverlap, ImVec2(0f, 0f))
        if (ImGui.isItemHovered()) {
            ImGui.beginTooltip()
            ImGui.text(conf.description)
            ImGui.endTooltip()
        }
    }

    fun widget(conf: Conf<in Any>) {
        val id = "##id_${conf.id}"
        var widget = conf.widget

        if (conf.value is Number) {
            if (conf.widget == null) {
                widget = Widget.INPUT
            }
        }

        when (conf.value) {
            is Long -> {
                val value = conf.value as Long? ?: 0
                run {
                    val imLong = ImLong(value)
                    val pData = longArrayOf(value)
                    if (conf is NumberConf<in Number>) {
                        val min = conf.range?.min?.toLong() ?: 0
                        val max = conf.range?.max?.toLong() ?: 0
                        val step = conf.step?.toLong()
                        when (widget) {
                            Widget.INPUT -> {
                                if (step != null) {
                                    if (ImGui.inputScalar(id, imLong, step)) {
                                        val get = imLong.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                } else {
                                    if (ImGui.inputScalar(id, imLong)) {
                                        val get = imLong.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                }
                                return@run
                            }

                            Widget.DRAG -> {
                                if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            Widget.SLIDER -> {
                                if (ImGui.sliderScalar(id, pData, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            else -> {
                                throw UnsupportedOperationException("Unsupported widget type: $widget")
                            }
                        }
                    }

                    if (ImGui.inputScalar(id, imLong)) {
                        conf.value = imLong.get()
                    }
                    return@run
                }
            }

            is Int -> {
                val value = conf.value as Int? ?: 0
                val imInt = ImInt(value)
                val pData = intArrayOf(value)
                run {
                    if (conf is NumberConf<in Number>) {
                        val min = conf.range?.min?.toInt() ?: 0
                        val max = conf.range?.max?.toInt() ?: 0
                        val step = conf.step?.toInt()

                        when (widget) {
                            Widget.INPUT -> {
                                if (step != null) {
                                    if (ImGui.inputScalar(id, imInt, step)) {
                                        val get = imInt.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                } else {
                                    if (ImGui.inputScalar(id, imInt)) {
                                        val get = imInt.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                }
                                return@run
                            }

                            Widget.DRAG -> {
                                if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            Widget.SLIDER -> {
                                if (ImGui.sliderScalar(id, pData, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            else -> {
                                throw UnsupportedOperationException("Unsupported widget type: $widget")
                            }
                        }
                    }

                    if (ImGui.inputScalar(id, imInt)) {
                        conf.value = imInt.get()
                    }
                    return@run
                }
            }

            is Short -> {
                val value = conf.value as Short? ?: 0
                val imShort = ImShort(value)
                val pData = shortArrayOf(value)
                run {
                    if (conf is NumberConf<in Number>) {
                        val min = conf.range?.min?.toShort() ?: 0
                        val max = conf.range?.max?.toShort() ?: 0
                        val step = conf.step?.toShort()

                        when (widget) {
                            Widget.INPUT -> {
                                if (step != null) {
                                    if (ImGui.inputScalar(id, imShort, step)) {
                                        val get = imShort.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                } else {
                                    if (ImGui.inputScalar(id, imShort)) {
                                        val get = imShort.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                }
                                return@run
                            }

                            Widget.DRAG -> {
                                if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            Widget.SLIDER -> {
                                if (ImGui.sliderScalar(id, pData, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            else -> {
                                throw UnsupportedOperationException("Unsupported widget type: $widget")
                            }
                        }
                    }

                    if (ImGui.inputScalar(id, imShort)) {
                        conf.value = imShort.get()
                    }
                    return@run
                }
            }

            is Byte -> {
                val value = conf.value as Byte? ?: 0
                val imShort = ImShort(value.toShort())
                val pData = shortArrayOf(value.toShort())
                run {
                    if (conf is NumberConf<in Number>) {
                        val min = conf.range?.min?.toShort() ?: 0
                        val max = conf.range?.max?.toShort() ?: 0
                        val step = conf.step?.toShort()

                        when (widget) {
                            Widget.INPUT -> {
                                if (step != null) {
                                    if (ImGui.inputScalar(id, imShort, step)) {
                                        val get = imShort.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                } else {
                                    if (ImGui.inputScalar(id, imShort)) {
                                        val get = imShort.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                }
                                return@run
                            }

                            Widget.DRAG -> {
                                if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            Widget.SLIDER -> {
                                if (ImGui.sliderScalar(id, pData, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            else -> {
                                throw UnsupportedOperationException("Unsupported widget type: $widget")
                            }
                        }
                    }

                    if (ImGui.inputScalar(id, imShort)) {
                        conf.value = imShort.get()
                    }
                    return@run
                }
            }

            is Float -> {
                val value = conf.value as Float? ?: 0f
                val imFloat = ImFloat(value)
                val pData = floatArrayOf(value)
                run {
                    if (conf is NumberConf<in Number>) {
                        val min = conf.range?.min?.toFloat() ?: 0f
                        val max = conf.range?.max?.toFloat() ?: 0f
                        val step = conf.step?.toFloat()

                        when (widget) {
                            Widget.INPUT -> {
                                if (step != null) {
                                    if (ImGui.inputScalar(id, imFloat, step)) {
                                        val get = imFloat.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                } else {
                                    if (ImGui.inputScalar(id, imFloat)) {
                                        val get = imFloat.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                }
                                return@run
                            }

                            Widget.DRAG -> {
                                if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            Widget.SLIDER -> {
                                if (ImGui.sliderScalar(id, pData, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            else -> {
                                throw UnsupportedOperationException("Unsupported widget type: $widget")
                            }
                        }
                    }

                    if (ImGui.inputScalar(id, imFloat)) {
                        conf.value = imFloat.get()
                    }
                    return@run
                }
            }

            is Double -> {
                val value = conf.value as Double? ?: 0.0
                val imDouble = ImDouble(value)
                val pData = doubleArrayOf(value)
                run {
                    if (conf is NumberConf<in Number>) {
                        val min = conf.range?.min?.toDouble() ?: 0.0
                        val max = conf.range?.max?.toDouble() ?: 0.0
                        val step = conf.step?.toDouble()

                        when (widget) {
                            Widget.INPUT -> {
                                if (step != null) {
                                    if (ImGui.inputScalar(id, imDouble, step)) {
                                        val get = imDouble.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                } else {
                                    if (ImGui.inputScalar(id, imDouble)) {
                                        val get = imDouble.get()
                                        if (get in min..max) {
                                            conf.value = get
                                        }
                                    }
                                }
                                return@run
                            }

                            Widget.DRAG -> {
                                if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            Widget.SLIDER -> {
                                if (ImGui.sliderScalar(id, pData, min, max)) {
                                    conf.value = pData[0]
                                }
                                return@run
                            }

                            else -> {
                                throw UnsupportedOperationException("Unsupported widget type: $widget")
                            }
                        }
                    }

                    if (ImGui.inputScalar(id, imDouble)) {
                        conf.value = imDouble.get()
                    }
                    return@run
                }
            }

            else -> {
                if (conf is OptionConf<in Any>) {
                    if (ImGui.beginCombo(id, conf.value?.toString() ?: "")) {
                        conf.options.forEach {
                            if (ImGui.selectable(it.toString())) {
                                conf.value = it!!
                            }
                        }
                        ImGui.endCombo()
                    }
                } else if (conf.value is String) {
                    val value = conf.value as String? ?: ""
                    val imString = ImString(value, 300)
                    if (ImGui.inputText(id, imString)) {
                        conf.value = imString.get()
                    }
                }
            }
        }
    }
}