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

package cn.enaium.mineconf.core.command.execute

import cn.enaium.mineconf.core.MineConfLoader
import cn.enaium.mineconf.core.command.literal
import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.core.common.text.Color
import cn.enaium.mineconf.core.utility.i18n
import cn.enaium.mineconf.core.utility.text
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun reload(): LiteralArgumentBuilder<CommonSource> {
    return literal("reload").executes {
        MineConfLoader.load()
        it.source.sendFeedback(i18n("command.reload.success").text().style {
            color = Color.GREEN
        })
        Command.SINGLE_SUCCESS
    }
}