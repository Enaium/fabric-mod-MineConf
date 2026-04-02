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

import cn.enaium.mineconf.core.MineConfLoader
import cn.enaium.mineconf.core.utility.ImRemember
import imgui.ImGui
import imgui.flag.ImGuiWindowFlags

/**
 * @author Enaium
 */
object MainPane {

    fun main() {
        main {
            MineConfLoader.MINE_CONF.forEach { (_, mineConf) ->
                if (ImGui.collapsingHeader(mineConf.name)) {
                    MineConfPane.mineConf(mineConf)
                }
            }
        }
    }

    fun main(content: () -> Unit) {
        val viewport = ImGui.getMainViewport()

        ImGui.setNextWindowPos(viewport.posX, viewport.posY)
        ImGui.setNextWindowSize(viewport.sizeX, viewport.sizeY)
        ImGui.setNextWindowViewport(viewport.id)

        ImRemember.beginState()
        if (ImGui.begin(
                "MineConf",
                (ImGuiWindowFlags.NoDecoration
                        or ImGuiWindowFlags.NoMove
                        or ImGuiWindowFlags.NoBringToFrontOnFocus)
            )
        ) {
            content()
            ImGui.end()
        }
        ImRemember.endState()
    }
}
