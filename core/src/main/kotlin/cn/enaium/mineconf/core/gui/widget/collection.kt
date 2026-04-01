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

import cn.enaium.mineconf.core.conf.CollectionConf
import cn.enaium.mineconf.core.utility.ImRemember
import imgui.ImGui
import imgui.ImVec2
import imgui.flag.ImGuiChildFlags
import imgui.type.ImString

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun CollectionConf<*>.collection(id: String) {
    this as CollectionConf<Any>
    ImGui.setNextItemWidth(-Float.MIN_VALUE)
    if (ImGui.beginListBox("${id}_list")) {
        this.value.forEachIndexed { index, item ->
            if (ImGui.selectable("${item}${id}_box_item")) {
            }
            ImGui.separator()

            if (ImGui.beginPopupContextItem("${id}_${index}_popup")) {
                if (ImGui.menuItem("Remove")) {
                    this.value = this.value.filterIndexed { filterIndex, _ -> filterIndex != index }
                }
                ImGui.endPopup()
            }
        }
        ImGui.endListBox()
    }

    ImGui.setNextWindowSizeConstraints(ImVec2(0f, 0f), ImVec2(Float.MAX_VALUE, Float.MAX_VALUE))
    ImGui.setNextItemWidth(-Float.MIN_VALUE)
    if (ImGui.beginCombo("${id}_combo", "")) {
        val input by ImRemember.remember { ImString() }
        ImGui.setNextItemWidth(ImGui.getContentRegionAvail().x - ImGui.calcTextSize("Add").x * 1.5f)
        ImGui.inputTextWithHint("${id}_search", "", input)
        ImGui.sameLine()
        if (ImGui.button("Add $id")) {
            if (this.options?.contains(input.get()) != false) {
                this.value += converter(input.get())
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
            this.options?.filter { it.toString().contains(input.get()) }?.forEach {
                if (ImGui.selectable(it.toString())) {
                    this.value += converter(it.toString())
                    ImGui.closeCurrentPopup()
                }
            }
            ImGui.endChild()
        }
        ImGui.endCombo()
    }
}