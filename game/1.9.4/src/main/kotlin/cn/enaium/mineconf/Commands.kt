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

package cn.enaium.mineconf

import cn.enaium.mineconf.command.MineConfCommand
import cn.enaium.mineconf.command.screen
import cn.enaium.mineconf.core.command.execute.*
import net.legacyfabric.fabric.api.registry.CommandRegistry

/**
 * @author Enaium
 */
object Commands {
    @JvmStatic
    fun client() {
        CommandRegistry.INSTANCE.register(MineConfCommand().apply {
            dispatcher.register(screen())
            dispatcher.register(set())
            dispatcher.register(get())
            dispatcher.register(append())
            dispatcher.register(remove())
            dispatcher.register(reset())
            dispatcher.register(reload())
        })
    }

    @JvmStatic
    fun server() {
        CommandRegistry.INSTANCE.register(MineConfCommand().apply {
            dispatcher.register(set())
            dispatcher.register(get())
            dispatcher.register(append())
            dispatcher.register(remove())
            dispatcher.register(reset())
            dispatcher.register(reload())
        })
    }
}