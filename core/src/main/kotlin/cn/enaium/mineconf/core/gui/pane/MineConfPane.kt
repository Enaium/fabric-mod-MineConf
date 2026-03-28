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

package cn.enaium.mineconf.core.gui.pane

import cn.enaium.mineconf.core.MineConf
import cn.enaium.mineconf.core.conf.*
import cn.enaium.mineconf.core.gui.widget.*
import imgui.ImGui
import imgui.ImVec2
import imgui.flag.ImGuiSelectableFlags
import imgui.flag.ImGuiTableColumnFlags
import imgui.flag.ImGuiTableFlags

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
object MineConfPane {

    fun mineConf(mineConf: MineConf) {
        MainPane.main {
            if (ImGui.beginTable(mineConf.id, 2, ImGuiTableFlags.Borders)) {
                ImGui.tableSetupColumn("Name", ImGuiTableColumnFlags.WidthFixed, 0f)
                ImGui.tableSetupColumn("Widget", ImGuiTableColumnFlags.WidthStretch, 1f)
                mineConf.getConf().forEach { (id, conf) ->
                    ImGui.tableNextRow()
                    ImGui.tableNextColumn()
                    name(conf)
                    ImGui.tableNextColumn()
                    widget(conf as Conf<Any>)
                }
                ImGui.endTable()
            }
        }
    }

    fun name(conf: Conf<*>) {
        ImGui.selectable(conf.name, false, ImGuiSelectableFlags.AllowItemOverlap, ImVec2(0f, 0f))
        if (ImGui.isItemHovered()) {
            ImGui.beginTooltip()
            ImGui.text(conf.description)
            ImGui.endTooltip()
        }
    }

    fun widget(conf: Conf<*>) {
        val id = "##id_${conf.id}"

        when (conf) {
            is NumberConf<*>,
            is Vec2Conf<*>,
            is Vec3Conf<*>,
            is Vec4Conf<*> -> {
                if (conf.widget == null) {
                    conf.widget = Widget.INPUT
                }
            }

            else -> when (conf.value) {
                is Number -> {
                    if (conf.widget == null) {
                        conf.widget = Widget.INPUT
                    }
                }
            }
        }

        when (conf) {
            is NumberConf<*> -> {
                conf.number(id)
            }

            is OptionConf<*> -> {
                conf.option(id)
            }

            is EnumConf<*> -> {
                conf.enum(id)
            }

            is Vec2Conf<*> -> {
                conf.vec2(id)
            }

            is Vec3Conf<*> -> {
                conf.vec3(id)
            }

            is Vec4Conf<*> -> {
                conf.vec4(id)
            }

            is CollectionConf<*> -> {
                conf.collection(id)
            }

            is MultimapConf<*, *> -> {
                conf.multimap(id)
            }

            else -> {
                conf.literal(id)
            }
        }
    }
}