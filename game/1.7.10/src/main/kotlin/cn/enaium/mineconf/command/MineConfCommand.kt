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

import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.core.common.text.Text
import cn.enaium.mineconf.utility.toMinecraft
import com.mojang.brigadier.CommandDispatcher
import net.minecraft.command.AbstractCommand
import net.minecraft.command.CommandSource

/**
 * @author Enaium
 */
class MineConfCommand : AbstractCommand() {

    val dispatcher = CommandDispatcher<CommonSource>()

    override fun compareTo(other: Any?): Int {
        return 0
    }


    override fun getCommandName(): String {
        return "mineconf"
    }

    override fun getUsageTranslationKey(source: CommandSource): String? {
        return null
    }

    override fun execute(commandSource: CommandSource, args: Array<String>) {
        val source = object : CommonSource {
            override fun sendFeedback(text: Text) {
                commandSource.sendMessage(text.toMinecraft())
            }
        }
        dispatcher.execute(dispatcher.parse(args.joinToString(" "), source))
    }

    override fun method_3276(
        commandSource: CommandSource,
        strings: Array<out String>
    ): List<String> {
        val source = object : CommonSource {
            override fun sendFeedback(text: Text) {
                commandSource.sendMessage(text.toMinecraft())
            }
        }
        return dispatcher.getCompletionSuggestions(
            dispatcher.parse(strings.joinToString(" "), source)
        )
            .get().list.map { it.text }
    }

    override fun getPermissionLevel(): Int {
        return 4
    }
}