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
import cn.enaium.mineconf.conf.*
import cn.enaium.mineconf.gui.widget.InputWidget
import cn.enaium.mineconf.gui.widget.Vec2Widget
import cn.enaium.mineconf.gui.widget.Vec3Widget
import cn.enaium.mineconf.gui.widget.Vec4Widget
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

        when (conf) {
            is NumberConf<*>,
            is Vec2Conf<*>,
            is Vec3Conf<*>,
            is Vec4Conf<*> -> {
                if (conf.widget == null) {
                    widget = Widget.INPUT
                }
            }

            else -> when (conf.value) {
                is Number -> {
                    if (conf.widget == null) {
                        widget = Widget.INPUT
                    }
                }
            }
        }

        when (conf.value) {
            is Long -> {
                val value = conf.value as Long? ?: 0
                val imLong = ImLong(value)
                if (conf is NumberConf<in Number>) {
                    val min = conf.range?.min?.toLong()
                    val max = conf.range?.max?.toLong()
                    val step = conf.step?.toLong()

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = imLong.get()
                    }
                } else {
                    if (InputWidget.input(id, widget, imLong)) {
                        conf.value = imLong.get()
                    }
                }
            }

            is Int -> {
                val value = conf.value as Int? ?: 0
                val imInt = ImInt(value)
                if (conf is NumberConf<in Number>) {
                    val min = conf.range?.min?.toInt()
                    val max = conf.range?.max?.toInt()
                    val step = conf.step?.toInt()

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = imInt.get()
                    }
                } else {
                    if (InputWidget.input(id, widget, imInt)) {
                        conf.value = imInt.get()
                    }
                }
            }

            is Short -> {
                val value = conf.value as Short? ?: 0
                val imShort = ImShort(value)
                if (conf is NumberConf<in Number>) {
                    val min = conf.range?.min?.toShort()
                    val max = conf.range?.max?.toShort()
                    val step = conf.step?.toShort()

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = imShort.get()
                    }
                } else {
                    if (InputWidget.input(id, widget, imShort)) {
                        conf.value = imShort.get()
                    }
                }
            }

            is Byte -> {
                val value = conf.value as Byte? ?: 0
                val imShort = ImShort(value.toShort())
                if (conf is NumberConf<in Number>) {
                    val min = conf.range?.min?.toByte()
                    val max = conf.range?.max?.toByte()
                    val step = conf.step?.toByte()

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = imShort.get()
                    }
                } else {
                    if (InputWidget.input(id, widget, imShort)) {
                        conf.value = imShort.get()
                    }
                }
            }

            is Float -> {
                val value = conf.value as Float? ?: 0.0f
                val imFloat = ImFloat(value)
                if (conf is NumberConf<in Number>) {
                    val min = conf.range?.min?.toFloat()
                    val max = conf.range?.max?.toFloat()
                    val step = conf.step?.toFloat()

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = imFloat.get()
                    }
                } else {
                    if (InputWidget.input(id, widget, imFloat)) {
                        conf.value = imFloat.get()
                    }
                }
            }

            is Double -> {
                val value = conf.value as Double? ?: 0.0
                val imDouble = ImDouble(value)
                if (conf is NumberConf<in Number>) {
                    val min = conf.range?.min?.toDouble()
                    val max = conf.range?.max?.toDouble()
                    val step = conf.step?.toDouble()

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = imDouble.get()
                    }
                } else {
                    if (InputWidget.input(id, widget, imDouble)) {
                        conf.value = imDouble.get()
                    }
                }
            }

            else -> {
                if (conf is OptionConf<Any>) {
                    if (ImGui.beginCombo(id, conf.value.toString())) {
                        conf.options.forEach {
                            if (ImGui.selectable(it.toString())) {
                                conf.value = it
                            }
                        }
                        ImGui.endCombo()
                    }
                } else if (conf is EnumConf<Any>) {
                    if (ImGui.beginCombo(id, conf.value.toString())) {
                        conf.type.enumConstants.forEach {
                            if (ImGui.selectable(it.toString())) {
                                conf.value = it!!
                            }
                        }
                        ImGui.endCombo()
                    }
                } else if (conf is Vec2Conf<*>) {
                    Vec2Widget.vec2(id, widget, conf)
                } else if (conf is Vec3Conf<*>) {
                    Vec3Widget.vec3(id, widget, conf)
                } else if (conf is Vec4Conf<*>) {
                    Vec4Widget.vec4(id, widget, conf)
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