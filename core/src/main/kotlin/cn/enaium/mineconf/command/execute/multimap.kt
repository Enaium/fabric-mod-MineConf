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

package cn.enaium.mineconf.command.execute

import cn.enaium.mineconf.conf.MultimapConf
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun MultimapConf<*, *>.append(id: LiteralArgumentBuilder<Any>) {
    this as MultimapConf<Any, Any>
    this.keyOptions?.forEach { keyOption ->
        val key = literal<Any>(keyOption.toString())
        this.valueOptions?.forEach { valueOption ->
            val value = literal<Any>(valueOption.toString()).executes {
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<Any, String>("value", StringArgumentType.string()).executes { context ->
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    } ?: run {
        val key = argument<Any, String>("key", StringArgumentType.string())
        this.valueOptions?.forEach { valueOption ->
            val value = literal<Any>(valueOption.toString()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<Any, String>("value", StringArgumentType.string()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    }
}

@Suppress("UNCHECKED_CAST")
fun MultimapConf<*, *>.remove(id: LiteralArgumentBuilder<Any>) {
    this as MultimapConf<Any, Any>
    this.keyOptions?.forEach { keyOption ->
        val key = literal<Any>(keyOption.toString()).executes {
            this.value = this.value.filter { it.key != keyOption }
            Command.SINGLE_SUCCESS
        }
        this.valueOptions?.forEach { valueOption ->
            val value = literal<Any>(valueOption.toString()).executes {
                this.value += this.value.filter { it.key != keyOption } + mapOf(
                    keyOption to (this.value[keyOption] ?: emptyList()) + valueOption
                )
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<Any, String>("value", StringArgumentType.string()).executes { context ->
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))
                this.value += this.value.filter { it.key != keyOption && it.value != valueOption }
                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    } ?: run {
        val key = argument<Any, String>("key", StringArgumentType.string())
        this.valueOptions?.forEach { valueOption ->
            val value = literal<Any>(valueOption.toString()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                this.value = this.value.filter { it.key != keyOption }
                Command.SINGLE_SUCCESS
            }
            key.then(value)
        } ?: run {
            key.then(argument<Any, String>("value", StringArgumentType.string()).executes { context ->
                val keyOption = this.keyConverter(StringArgumentType.getString(context, "key"))
                val valueOption = this.valueConverter(StringArgumentType.getString(context, "value"))

                this.value = this.value.filter { it.key != keyOption && it.value != valueOption }

                Command.SINGLE_SUCCESS
            })
        }
        id.then(key)
    }
}