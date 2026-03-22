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

package cn.enaium.mineconf.command.type

import cn.enaium.mineconf.MineConf
import cn.enaium.mineconf.MineConfLoader
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import java.util.concurrent.CompletableFuture

/**
 * @author Enaium
 */
class MineConfArgumentType : ArgumentType<MineConf> {

    companion object {
        fun mineConf(): MineConfArgumentType {
            return MineConfArgumentType()
        }

        fun getMineConf(context: CommandContext<*>, name: String): MineConf {
            return context.getArgument(name, MineConf::class.java)
        }
    }

    override fun parse(reader: StringReader): MineConf {
        return MineConfLoader.MINE_CONF[reader.readString()]
            ?: throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().createWithContext(reader)
    }

    override fun <S> listSuggestions(
        context: CommandContext<S>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions> {
        return builder.createOffset(builder.start).apply {
            MineConfLoader.MINE_CONF.keys.forEach {
                suggest(it)
            }
        }.buildFuture()
    }

    override fun getExamples(): Collection<String> {
        return listOf("mineconf")
    }
}