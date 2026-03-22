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

package cn.enaium.mineconf.command

import cn.enaium.mineconf.CLIENT_ROOT
import cn.enaium.mineconf.command.type.MineConfArgumentType
import cn.enaium.mineconf.screen.MainScreen
import cn.enaium.mineconf.screen.MineConfScreen
import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource
import net.minecraft.client.MinecraftClient

/**
 * @author Enaium
 */
fun screen(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
    dispatcher.register(
        CLIENT_ROOT.then(
            ClientCommandManager.literal("screen").executes {
                MinecraftClient.getInstance()
                    .execute { MinecraftClient.getInstance().openScreen(MainScreen()) }
                Command.SINGLE_SUCCESS
            }.then(ClientCommandManager.argument("id", MineConfArgumentType.mineConf()).executes { context ->
                MinecraftClient.getInstance().execute {
                    MinecraftClient.getInstance()
                        .openScreen(MineConfScreen(MineConfArgumentType.getMineConf(context, "id")))
                }
                Command.SINGLE_SUCCESS
            })
        )
    )
}