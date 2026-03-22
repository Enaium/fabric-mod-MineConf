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

import cn.enaium.mineconf.conf.NumberConf
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.DoubleArgumentType
import com.mojang.brigadier.arguments.FloatArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.LongArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun NumberConf<*>.number(id: LiteralArgumentBuilder<Any>) {
    when (this.value) {
        is Long -> {
            this as NumberConf<Long>
            this.range?.also {
                id.then(
                    argument<Any, Long>(
                        "value",
                        LongArgumentType.longArg(it.min, it.max)
                    ).executes { context ->
                        this.value = LongArgumentType.getLong(context, "value")
                        Command.SINGLE_SUCCESS
                    })
            }
        }

        is Int -> {
            this as NumberConf<Int>
            this.range?.also {
                id.then(
                    argument<Any, Int>(
                        "value",
                        IntegerArgumentType.integer(it.min, it.max)
                    ).executes { context ->
                        this.value = IntegerArgumentType.getInteger(context, "value")
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Short -> {
            this as NumberConf<Short>
            this.range?.also {
                id.then(
                    argument<Any, Int>(
                        "value",
                        IntegerArgumentType.integer(it.min.toInt(), it.max.toInt())
                    ).executes { context ->
                        this.value = IntegerArgumentType.getInteger(context, "value").toShort()
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Byte -> {
            this as NumberConf<Byte>
            this.range?.also {
                id.then(
                    argument<Any, Int>(
                        "value",
                        IntegerArgumentType.integer(it.min.toInt(), it.max.toInt())
                    ).executes { context ->
                        this.value = IntegerArgumentType.getInteger(context, "value").toByte()
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Float -> {
            this as NumberConf<Float>
            this.range?.also {
                id.then(
                    argument<Any, Float>(
                        "value",
                        FloatArgumentType.floatArg(it.min, it.max)
                    ).executes { context ->
                        this.value = FloatArgumentType.getFloat(context, "value")
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }

        is Double -> {
            this as NumberConf<Double>
            this.range?.also {
                id.then(
                    argument<Any, Double>(
                        "value",
                        DoubleArgumentType.doubleArg(it.min, it.max)
                    ).executes { context ->
                        this.value = DoubleArgumentType.getDouble(context, "value")
                        Command.SINGLE_SUCCESS
                    }
                )
            }
        }
    }
}