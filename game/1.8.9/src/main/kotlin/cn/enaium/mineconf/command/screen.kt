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

import cn.enaium.mineconf.core.command.argument
import cn.enaium.mineconf.core.command.literal
import cn.enaium.mineconf.core.command.type.MineConfArgumentType
import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.screen.MainScreen
import cn.enaium.mineconf.screen.MineConfScreen
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.client.MinecraftClient

/**
 * @author Enaium
 */
fun screen(): LiteralArgumentBuilder<CommonSource> {
    return literal("screen").executes {
        Thread {
            MinecraftClient.getInstance()
                .execute { MinecraftClient.getInstance().setScreen(MainScreen()) }
        }.start()
        Command.SINGLE_SUCCESS
    }.then(argument("id", MineConfArgumentType.mineConf()).executes { context ->
        Thread {
            MinecraftClient.getInstance().execute {
                MinecraftClient.getInstance()
                    .setScreen(MineConfScreen(MineConfArgumentType.getMineConf(context, "id")))
            }
        }.start()
        Command.SINGLE_SUCCESS
    })
}