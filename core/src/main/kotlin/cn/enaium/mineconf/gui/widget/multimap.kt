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

package cn.enaium.mineconf.gui.widget

import cn.enaium.mineconf.conf.MultimapConf
import cn.enaium.mineconf.utility.ImRemember.remember
import imgui.ImGui
import imgui.ImVec2
import imgui.flag.ImGuiChildFlags
import imgui.type.ImString

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun MultimapConf<*, *>.multimap(id: String) {
    this as MultimapConf<Any, Any>

    var currentKey by remember { null as Any? }

    val width = ImGui.getContentRegionAvail().x
    val spacing = ImGui.getStyle().itemSpacing.x
    val half = (width - spacing) * 0.5f

    if (ImGui.beginChild(
            "${id}_left",
            ImVec2(half, 0f),
            ImGuiChildFlags.AutoResizeY or ImGuiChildFlags.AlwaysAutoResize
        )
    ) {
        if (ImGui.beginListBox("${id}_key_list")) {
            this.value.keys.forEachIndexed { index, item ->
                if (ImGui.selectable("${item}${id}_box_item", item == currentKey)) {
                    currentKey = item
                }
                if (ImGui.beginPopupContextItem("${id}_${index}_popup")) {
                    if (ImGui.menuItem("Remove")) {
                        this.value = this.value.filter { it.key != currentKey }
                        currentKey = null
                    }
                    ImGui.endPopup()
                }

                ImGui.separator()
            }
            ImGui.endListBox()
        }

        ImGui.setNextWindowSizeConstraints(ImVec2(0f, 0f), ImVec2(Float.MAX_VALUE, Float.MAX_VALUE))
        if (ImGui.beginCombo("${id}_key_combo", "")) {
            val input by remember { ImString("") }
            ImGui.inputTextWithHint("${id}_search", "", input)
            ImGui.sameLine()
            if (ImGui.button("Add $id")) {
                if (this.keyOptions?.contains(input.get()) != false) {
                    this.value = this.value + mapOf(keyConverter(input.get()) to emptyList())
                }
                ImGui.closeCurrentPopup()
            }
            ImGui.setNextWindowSizeConstraints(ImVec2(0f, 0f), ImVec2(Float.MAX_VALUE, 200f))
            if (ImGui.beginChild(
                    "${id}_child",
                    ImVec2(),
                    ImGuiChildFlags.AutoResizeY or ImGuiChildFlags.AlwaysAutoResize
                )
            ) {
                this.keyOptions?.filter { it.toString().contains(input.get()) }?.forEach {
                    if (ImGui.selectable("${it}${id}")) {
                        this.value = this.value + mapOf(keyConverter(it.toString()) to emptyList())
                        ImGui.closeCurrentPopup()
                    }
                }
                ImGui.endChild()
            }
            ImGui.endCombo()
        }
        ImGui.endChild()
    }
    ImGui.sameLine()
    if (ImGui.beginChild(
            "${id}_right",
            ImVec2(half, 0f),
            ImGuiChildFlags.AutoResizeY or ImGuiChildFlags.AlwaysAutoResize
        )
    ) {
        ImGui.beginDisabled(currentKey == null)
        if (ImGui.beginListBox("${id}_value_list")) {
            this.value[currentKey]?.forEachIndexed { index, item ->
                if (ImGui.selectable("${item}${id}_box_item")) {
                }
                if (ImGui.beginPopupContextItem("${id}_${index}_popup")) {
                    if (ImGui.menuItem("Remove")) {
                        this.value =
                            ((this.value.filter { it.key != currentKey } + mapOf(currentKey to this.value[currentKey]?.filterIndexed { filterIndex, _ -> filterIndex != index })) as Map<Any, Collection<Any>>)
                    }
                    ImGui.endPopup()
                }
                ImGui.separator()
            }
            ImGui.endListBox()
        }

        ImGui.setNextWindowSizeConstraints(ImVec2(0f, 0f), ImVec2(Float.MAX_VALUE, Float.MAX_VALUE))
        if (ImGui.beginCombo("${id}_value_combo", "")) {
            val input by remember { ImString("") }
            ImGui.inputTextWithHint("${id}_search", "", input)
            ImGui.sameLine()
            if (ImGui.button("Add $id")) {
                if (this.valueOptions?.contains(input.get()) != false) {
                    this.value =
                        ((this.value.filter { it.key != currentKey } + mapOf(
                            currentKey to (this.value[currentKey] ?: emptyList()) + valueConverter(input.get())
                        )) as Map<Any, Collection<Any>>)
                }
                ImGui.closeCurrentPopup()
            }
            ImGui.setNextWindowSizeConstraints(ImVec2(0f, 0f), ImVec2(Float.MAX_VALUE, 200f))
            if (ImGui.beginChild(
                    "${id}_child",
                    ImVec2(),
                    ImGuiChildFlags.AutoResizeY or ImGuiChildFlags.AlwaysAutoResize
                )
            ) {
                this.valueOptions?.filter { it.toString().contains(input.get()) }?.forEach {
                    if (ImGui.selectable(it.toString())) {
                        this.value =
                            ((this.value.filter { it.key != currentKey } + mapOf(
                                currentKey to (this.value[currentKey] ?: emptyList()) + valueConverter(it.toString())
                            )) as Map<Any, Collection<Any>>)
                        ImGui.closeCurrentPopup()
                    }
                }
                ImGui.endChild()
            }
            ImGui.endCombo()
        }
        ImGui.endDisabled()
        ImGui.endChild()
    }
}