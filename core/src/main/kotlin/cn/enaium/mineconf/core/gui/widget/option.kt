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

import cn.enaium.mineconf.core.conf.OptionConf
import imgui.ImGui

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun OptionConf<*>.option(id: String) {
    this as OptionConf<Any>
    if (ImGui.beginCombo(id, this.value.toString())) {
        this.options.forEach { option ->
            if (ImGui.selectable(option.toString())) {
                this.value = option
            }
        }
        ImGui.endCombo()
    }
}