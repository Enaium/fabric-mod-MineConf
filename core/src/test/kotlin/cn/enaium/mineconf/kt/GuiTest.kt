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

package cn.enaium.mineconf.kt

import cn.enaium.mineconf.core.MineConfLoader
import cn.enaium.mineconf.core.gui.MineConfGui
import cn.enaium.mineconf.core.gui.pane.MainPane
import imgui.ImGui
import imgui.app.Application
import imgui.app.Application.launch
import imgui.app.Configuration
import org.junit.jupiter.api.Test

/**
 * @author Enaium
 */
class GuiTest {
    @Test
    fun mineConf() {
        System.setProperty("user.dir", "run")

        launch(object : Application() {
            override fun init(config: Configuration) {
                MineConfLoader.load()
                super.init(config)
                ImGui.getIO().iniFilename = null
                MineConfGui.initFonts()
                MineConfGui.initStyle()
            }

            override fun process() {
                MainPane.main()
            }

            override fun dispose() {
                MineConfLoader.save()
                super.dispose()
            }
        })
    }
}