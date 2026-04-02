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

import cn.enaium.mineconf.core.command.argument
import cn.enaium.mineconf.core.command.literal
import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.core.common.text.Color
import cn.enaium.mineconf.core.conf.MultimapConf
import cn.enaium.mineconf.core.utility.i18n
import cn.enaium.mineconf.core.utility.text
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun MultimapConf<*, *>.append(id: LiteralArgumentBuilder<CommonSource>) {
    this as MultimapConf<Any, Any>
    this.keyOptions?.forEach { keyOption ->
        val key = literal(keyOption.toString())
        this.valueOptions?.forEach { valueOption ->
            val value = literal(valueOption.toString()).executes { context ->
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                context.source.sendFeedback(i18n("command.append.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<String>("value", StringArgumentType.string()).executes { context ->
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                context.source.sendFeedback(i18n("command.append.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    } ?: run {
        val key = argument<String>("key", StringArgumentType.string())
        this.valueOptions?.forEach { valueOption ->
            val value = literal(valueOption.toString()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                context.source.sendFeedback(i18n("command.append.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<String>("value", StringArgumentType.string()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                context.source.sendFeedback(i18n("command.append.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    }
}

@Suppress("UNCHECKED_CAST")
fun MultimapConf<*, *>.remove(id: LiteralArgumentBuilder<CommonSource>) {
    this as MultimapConf<Any, Any>
    this.keyOptions?.forEach { keyOption ->
        val key = literal(keyOption.toString()).executes { context ->
            this.value = this.value.filter { it.key != keyOption }
            context.source.sendFeedback(i18n("command.remove.success").text().style {
                color = Color.GREEN
            })
            Command.SINGLE_SUCCESS
        }
        this.valueOptions?.forEach { valueOption ->
            val value = literal(valueOption.toString()).executes { context ->
                this.value = this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) - valueOption
                )
                context.source.sendFeedback(i18n("command.remove.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<String>("value", StringArgumentType.string()).executes { context ->
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))
                this.value = this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) - valueOption
                )

                context.source.sendFeedback(i18n("command.remove.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    } ?: run {
        val key = argument<String>("key", StringArgumentType.string()).executes { context ->
            val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
            this.value = this.value.filter { it.key != keyOption }
            context.source.sendFeedback(i18n("command.remove.success").text().style {
                color = Color.GREEN
            })
            Command.SINGLE_SUCCESS
        }
        this.valueOptions?.forEach { valueOption ->
            val value = literal(valueOption.toString()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                this.value = this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) - valueOption
                )
                context.source.sendFeedback(i18n("command.remove.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<String>("value", StringArgumentType.string()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))

                this.value = this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) - valueOption
                )
                context.source.sendFeedback(i18n("command.remove.success").text().style {
                    color = Color.GREEN
                })
                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    }
}