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
import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.core.conf.NumberConf
import cn.enaium.mineconf.core.utility.i18n
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.DoubleArgumentType
import com.mojang.brigadier.arguments.FloatArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.LongArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun NumberConf<*>.number(id: LiteralArgumentBuilder<CommonSource>) {
    when (this.value) {
        is Long -> {
            this as NumberConf<Long>
            this.range?.also {
                id.then(
                    argument<Long>(
                        "value",
                        LongArgumentType.longArg(it.min, it.max)
                    ).executes { context ->
                        this.value = LongArgumentType.getLong(context, "value")
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    })
            }
        }

        is Int -> {
            this as NumberConf<Int>
            this.range?.also {
                id.then(
                    argument<Int>(
                        "value",
                        IntegerArgumentType.integer(it.min, it.max)
                    ).executes { context ->
                        this.value = IntegerArgumentType.getInteger(context, "value")
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Short -> {
            this as NumberConf<Short>
            this.range?.also {
                id.then(
                    argument<Int>(
                        "value",
                        IntegerArgumentType.integer(it.min.toInt(), it.max.toInt())
                    ).executes { context ->
                        this.value = IntegerArgumentType.getInteger(context, "value").toShort()
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Byte -> {
            this as NumberConf<Byte>
            this.range?.also {
                id.then(
                    argument<Int>(
                        "value",
                        IntegerArgumentType.integer(it.min.toInt(), it.max.toInt())
                    ).executes { context ->
                        this.value = IntegerArgumentType.getInteger(context, "value").toByte()
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Float -> {
            this as NumberConf<Float>
            this.range?.also {
                id.then(
                    argument<Float>(
                        "value",
                        FloatArgumentType.floatArg(it.min, it.max)
                    ).executes { context ->
                        this.value = FloatArgumentType.getFloat(context, "value")
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Double -> {
            this as NumberConf<Double>
            this.range?.also {
                id.then(
                    argument<Double>(
                        "value",
                        DoubleArgumentType.doubleArg(it.min, it.max)
                    ).executes { context ->
                        this.value = DoubleArgumentType.getDouble(context, "value")
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }
    }
}