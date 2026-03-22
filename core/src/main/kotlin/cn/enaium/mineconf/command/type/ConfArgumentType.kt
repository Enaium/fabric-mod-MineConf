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

import cn.enaium.mineconf.MineConfLoader
import cn.enaium.mineconf.conf.Conf
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
class ConfArgumentType : ArgumentType<Conf<*>> {
    companion object {
        fun conf(): ConfArgumentType {
            return ConfArgumentType()
        }

        fun getConf(context: CommandContext<*>, name: String): Conf<*> {
            return context.getArgument(name, Conf::class.java)
        }
    }

    override fun parse(reader: StringReader): Conf<*> {
        val modId = reader.readString()
        var result: Conf<*>? = null
        if (reader.read() == '[') {
            val tag = reader.readString()
            if (reader.read() == '=') {
                val value = reader.readString()

                when (tag) {
                    "id" -> {
                        MineConfLoader.MINE_CONF[modId]?.getConf(value)?.also {
                            result = it
                        }
                    }

                    "name" -> {
                        MineConfLoader.MINE_CONF[modId]?.getConf()?.forEach { (_, conf) ->
                            if (conf.name == value) {
                                result = conf
                            }
                        }
                    }
                }
            }
        }

        if (reader.read() == ']') {
            result?.also {
                return it
            }
        }

        throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().createWithContext(reader)
    }

    override fun <S> listSuggestions(
        context: CommandContext<S>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions> {
        val reader = StringReader(builder.remaining)
        val modId = reader.readString()

        if (!reader.canRead()) {
            return builder.createOffset(builder.start).apply {
                MineConfLoader.MINE_CONF.keys.forEach {
                    suggest("${it}[")
                }
            }.buildFuture()
        }

        if (reader.read() == '[') {
            val tag = reader.readString()
            if (!reader.canRead()) {
                return builder.createOffset(builder.start + (reader.cursor - tag.length)).apply {
                    listOf("id", "name").forEach {
                        suggest("${it}=")
                    }
                }.buildFuture()
            }
            if (reader.read() == '=') {
                val value = reader.readString()
                if (!reader.canRead()) {
                    return builder.createOffset(builder.start + (reader.cursor - value.length)).apply {
                        when (tag) {
                            "id" -> {
                                MineConfLoader.MINE_CONF[modId]?.getConf()?.keys?.forEach {
                                    suggest("$it]")
                                }
                            }

                            "name" -> {
                                MineConfLoader.MINE_CONF[modId]?.getConf()?.forEach { (_, conf) ->
                                    suggest("${conf.name}]")
                                }
                            }
                        }
                    }.buildFuture()
                }
            }
        }
        return builder.buildFuture()
    }

    override fun getExamples(): Collection<String> {
        return listOf("mineconf[foo=bar]")
    }
}