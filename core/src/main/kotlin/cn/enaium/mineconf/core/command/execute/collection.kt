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
import cn.enaium.mineconf.core.conf.CollectionConf
import cn.enaium.mineconf.core.utility.i18n
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun CollectionConf<*>.append(id: LiteralArgumentBuilder<CommonSource>) {
    this as CollectionConf<Any>
    this.options?.forEach {
        id.then(literal(it.toString()).executes { _ ->
            this.value += it
            Command.SINGLE_SUCCESS
        })
    } ?: run {
        id.then(argument<String>("value", StringArgumentType.string()).executes { context ->
            this.value += this.converter(StringArgumentType.getString(context, "value"))
            context.source.sendFeedback(i18n("command.append.success"))
            Command.SINGLE_SUCCESS
        })
    }
}

@Suppress("UNCHECKED_CAST")
fun CollectionConf<*>.remove(id: LiteralArgumentBuilder<CommonSource>) {
    this as CollectionConf<Any>
    this.options?.forEach {
        id.then(literal(it.toString()).executes { context ->
            this.value -= it
            context.source.sendFeedback(i18n("command.remove.success"))
            Command.SINGLE_SUCCESS
        })
    } ?: run {
        id.then(argument<String>("value", StringArgumentType.string()).executes { context ->
            this.value -= this.converter(StringArgumentType.getString(context, "value"))
            Command.SINGLE_SUCCESS
        })
    }
}