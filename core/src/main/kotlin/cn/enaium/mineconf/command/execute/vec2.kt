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

import cn.enaium.mineconf.command.argument
import cn.enaium.mineconf.common.CommonSource
import cn.enaium.mineconf.conf.Vec2Conf
import cn.enaium.mineconf.type.Vec2
import cn.enaium.mineconf.utility.i18n
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
fun Vec2Conf<*>.vec2(id: LiteralArgumentBuilder<CommonSource>) {
    when (this.value.x) {
        is Long -> {
            this as Vec2Conf<Long>
            id.then(
                argument<Long>(
                    "x",
                    if (this.rangeX != null) {
                        LongArgumentType.longArg(
                            this.rangeX.min,
                            this.rangeX.max
                        )
                    } else {
                        LongArgumentType.longArg()
                    }
                ).then(
                    argument<Long>(
                        "y", if (this.rangeY != null) {
                            LongArgumentType.longArg(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            LongArgumentType.longArg()
                        }
                    ).executes { context ->
                        val x = LongArgumentType.getLong(context, "x")
                        val y = LongArgumentType.getLong(context, "y")
                        this.value = Vec2(x, y)
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    })
            )
        }

        is Int -> {
            this as Vec2Conf<Int>
            id.then(
                argument<Int>(
                    "x",
                    if (this.rangeX != null) {
                        IntegerArgumentType.integer(
                            this.rangeX.min,
                            this.rangeX.max
                        )
                    } else {
                        IntegerArgumentType.integer()
                    }
                ).then(
                    argument<Int>(
                        "y", if (this.rangeY != null) {
                            IntegerArgumentType.integer(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            IntegerArgumentType.integer()
                        }
                    ).executes { context ->
                        val x = IntegerArgumentType.getInteger(context, "x")
                        val y = IntegerArgumentType.getInteger(context, "y")
                        this.value = Vec2(x, y)
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    })
            )
        }

        is Short -> {
            this as Vec2Conf<Short>
            id.then(
                argument<Int>(
                    "x",
                    if (this.rangeX != null) {
                        IntegerArgumentType.integer(
                            this.rangeX.min.toInt(),
                            this.rangeX.max.toInt()
                        )
                    } else {
                        IntegerArgumentType.integer()
                    }
                ).then(
                    argument<Int>(
                        "y", if (this.rangeY != null) {
                            IntegerArgumentType.integer(
                                this.rangeY.min.toInt(),
                                this.rangeY.max.toInt()
                            )
                        } else {
                            IntegerArgumentType.integer()
                        }
                    ).executes { context ->
                        val x = IntegerArgumentType.getInteger(context, "x").toShort()
                        val y = IntegerArgumentType.getInteger(context, "y").toShort()
                        this.value = Vec2(x, y)
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    })
            )
        }

        is Byte -> {
            this as Vec2Conf<Byte>
            id.then(
                argument<Int>(
                    "x",
                    if (this.rangeX != null) {
                        IntegerArgumentType.integer(
                            this.rangeX.min.toInt(),
                            this.rangeX.max.toInt()
                        )
                    } else {
                        IntegerArgumentType.integer()
                    }
                ).then(
                    argument<Int>(
                        "y", if (this.rangeY != null) {
                            IntegerArgumentType.integer(
                                this.rangeY.min.toInt(),
                                this.rangeY.max.toInt()
                            )
                        } else {
                            IntegerArgumentType.integer()
                        }
                    ).executes { context ->
                        val x = IntegerArgumentType.getInteger(context, "x").toByte()
                        val y = IntegerArgumentType.getInteger(context, "y").toByte()
                        this.value = Vec2(x, y)
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    })
            )
        }

        is Float -> {
            this as Vec2Conf<Float>
            id.then(
                argument<Float>(
                    "x",
                    if (this.rangeX != null) {
                        FloatArgumentType.floatArg(
                            this.rangeX.min,
                            this.rangeX.max
                        )
                    } else {
                        FloatArgumentType.floatArg()
                    }
                ).then(
                    argument<Float>(
                        "y", if (this.rangeY != null) {
                            FloatArgumentType.floatArg(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            FloatArgumentType.floatArg()
                        }
                    ).executes { context ->
                        val x = FloatArgumentType.getFloat(context, "x")
                        val y = FloatArgumentType.getFloat(context, "y")
                        this.value = Vec2(x, y)
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    })
            )
        }

        is Double -> {
            this as Vec2Conf<Double>
            id.then(
                argument<Double>(
                    "x",
                    if (this.rangeX != null) {
                        DoubleArgumentType.doubleArg(
                            this.rangeX.min,
                            this.rangeX.max
                        )
                    } else {
                        DoubleArgumentType.doubleArg()
                    }
                ).then(
                    argument<Double>(
                        "y", if (this.rangeY != null) {
                            DoubleArgumentType.doubleArg(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            DoubleArgumentType.doubleArg()
                        }
                    ).executes { context ->
                        val x = DoubleArgumentType.getDouble(context, "x")
                        val y = DoubleArgumentType.getDouble(context, "y")
                        this.value = Vec2(x, y)
                        context.source.sendFeedback(i18n("command.set.success"))
                        Command.SINGLE_SUCCESS
                    })
            )
        }
    }
}
