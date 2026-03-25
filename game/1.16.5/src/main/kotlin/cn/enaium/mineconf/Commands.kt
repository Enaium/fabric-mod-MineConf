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


import cn.enaium.mineconf.command.execute.append
import cn.enaium.mineconf.command.execute.remove
import cn.enaium.mineconf.command.execute.set
import cn.enaium.mineconf.command.execute.get
import cn.enaium.mineconf.command.screen
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.minecraft.server.command.ServerCommandSource

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
object Commands {
    @JvmStatic
    fun client() {
        screen(ClientCommandManager.DISPATCHER)
        ClientCommandManager.DISPATCHER.register(CLIENT_ROOT.then(set() as LiteralArgumentBuilder<FabricClientCommandSource>))
        ClientCommandManager.DISPATCHER.register(CLIENT_ROOT.then(get() as LiteralArgumentBuilder<FabricClientCommandSource>))
        ClientCommandManager.DISPATCHER.register(CLIENT_ROOT.then(append() as LiteralArgumentBuilder<FabricClientCommandSource>))
        ClientCommandManager.DISPATCHER.register(CLIENT_ROOT.then(remove() as LiteralArgumentBuilder<FabricClientCommandSource>))
    }

    @JvmStatic
    fun server() {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource>, _ ->
            dispatcher.register(SERVER_ROOT.then(set() as LiteralArgumentBuilder<ServerCommandSource>))
            dispatcher.register(SERVER_ROOT.then(get() as LiteralArgumentBuilder<ServerCommandSource>))
            dispatcher.register(SERVER_ROOT.then(append() as LiteralArgumentBuilder<ServerCommandSource>))
            dispatcher.register(SERVER_ROOT.then(remove() as LiteralArgumentBuilder<ServerCommandSource>))
        })
    }
}