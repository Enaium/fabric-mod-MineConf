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

import cn.enaium.mineconf.command.screen
import cn.enaium.mineconf.core.command.execute.*
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.commands.CommandSourceStack

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
object Commands {
    @JvmStatic
    fun client() {
        ClientCommandRegistrationCallback.EVENT.register(ClientCommandRegistrationCallback { dispatcher: CommandDispatcher<FabricClientCommandSource>, _ ->
            if (FabricLoader.getInstance().allMods.any { it.metadata.id == "fabric-gui-imgui" }) {
                screen(dispatcher)
            }
            dispatcher.register(CLIENT_ROOT.then(set() as LiteralArgumentBuilder<FabricClientCommandSource>))
            dispatcher.register(CLIENT_ROOT.then(get() as LiteralArgumentBuilder<FabricClientCommandSource>))
            dispatcher.register(CLIENT_ROOT.then(append() as LiteralArgumentBuilder<FabricClientCommandSource>))
            dispatcher.register(CLIENT_ROOT.then(remove() as LiteralArgumentBuilder<FabricClientCommandSource>))
            dispatcher.register(CLIENT_ROOT.then(reset() as LiteralArgumentBuilder<FabricClientCommandSource>))
            dispatcher.register(CLIENT_ROOT.then(reload() as LiteralArgumentBuilder<FabricClientCommandSource>))
        })
    }

    @JvmStatic
    fun server() {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher: CommandDispatcher<CommandSourceStack>, _, _ ->
            dispatcher.register(SERVER_ROOT.then(set() as LiteralArgumentBuilder<CommandSourceStack>))
            dispatcher.register(SERVER_ROOT.then(get() as LiteralArgumentBuilder<CommandSourceStack>))
            dispatcher.register(SERVER_ROOT.then(append() as LiteralArgumentBuilder<CommandSourceStack>))
            dispatcher.register(SERVER_ROOT.then(remove() as LiteralArgumentBuilder<CommandSourceStack>))
            dispatcher.register(SERVER_ROOT.then(reset() as LiteralArgumentBuilder<CommandSourceStack>))
            dispatcher.register(SERVER_ROOT.then(reload() as LiteralArgumentBuilder<CommandSourceStack>))
        })
    }
}