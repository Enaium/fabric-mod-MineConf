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

import cn.enaium.mineconf.conf.Vec3Conf
import cn.enaium.mineconf.type.Vec3
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
fun Vec3Conf<*>.vec3(id: LiteralArgumentBuilder<Any>) {
    when (this.value.x) {
        is Long -> {
            this as Vec3Conf<Long>
            id.then(
                argument<Any, Long>(
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
                    argument<Any, Long>(
                        "y", if (this.rangeY != null) {
                            LongArgumentType.longArg(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            LongArgumentType.longArg()
                        }
                    ).then(
                        argument<Any, Long>(
                            "z", if (this.rangeZ != null) {
                                LongArgumentType.longArg(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                LongArgumentType.longArg()
                            }
                        ).executes { context ->
                            val x = LongArgumentType.getLong(context, "x")
                            val y = LongArgumentType.getLong(context, "y")
                            val z = LongArgumentType.getLong(context, "z")
                            this.value = Vec3(x, y, z)
                            Command.SINGLE_SUCCESS
                        })
                )
            )
        }

        is Int -> {
            this as Vec3Conf<Int>
            id.then(
                argument<Any, Int>(
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
                    argument<Any, Int>(
                        "y", if (this.rangeY != null) {
                            IntegerArgumentType.integer(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            IntegerArgumentType.integer()
                        }
                    ).then(
                        argument<Any, Int>(
                            "z", if (this.rangeZ != null) {
                                IntegerArgumentType.integer(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                IntegerArgumentType.integer()
                            }
                        ).executes { context ->
                            val x = IntegerArgumentType.getInteger(context, "x")
                            val y = IntegerArgumentType.getInteger(context, "y")
                            val z = IntegerArgumentType.getInteger(context, "z")
                            this.value = Vec3(x, y, z)
                            Command.SINGLE_SUCCESS
                        })
                )
            )
        }

        is Short -> {
            this as Vec3Conf<Short>
            id.then(
                argument<Any, Int>(
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
                    argument<Any, Int>(
                        "y", if (this.rangeY != null) {
                            IntegerArgumentType.integer(
                                this.rangeY.min.toInt(),
                                this.rangeY.max.toInt()
                            )
                        } else {
                            IntegerArgumentType.integer()
                        }
                    ).then(
                        argument<Any, Int>(
                            "z", if (this.rangeZ != null) {
                                IntegerArgumentType.integer(
                                    this.rangeZ.min.toInt(),
                                    this.rangeZ.max.toInt()
                                )
                            } else {
                                IntegerArgumentType.integer()
                            }
                        ).executes { context ->
                            val x = IntegerArgumentType.getInteger(context, "x").toShort()
                            val y = IntegerArgumentType.getInteger(context, "y").toShort()
                            val z = IntegerArgumentType.getInteger(context, "z").toShort()
                            this.value = Vec3(x, y, z)
                            Command.SINGLE_SUCCESS
                        })
                )
            )
        }

        is Byte -> {
            this as Vec3Conf<Byte>
            id.then(
                argument<Any, Int>(
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
                    argument<Any, Int>(
                        "y", if (this.rangeY != null) {
                            IntegerArgumentType.integer(
                                this.rangeY.min.toInt(),
                                this.rangeY.max.toInt()
                            )
                        } else {
                            IntegerArgumentType.integer()
                        }
                    ).then(
                        argument<Any, Int>(
                            "z", if (this.rangeZ != null) {
                                IntegerArgumentType.integer(
                                    this.rangeZ.min.toInt(),
                                    this.rangeZ.max.toInt()
                                )
                            } else {
                                IntegerArgumentType.integer()
                            }
                        ).executes { context ->
                            val x = IntegerArgumentType.getInteger(context, "x").toByte()
                            val y = IntegerArgumentType.getInteger(context, "y").toByte()
                            val z = IntegerArgumentType.getInteger(context, "z").toByte()
                            this.value = Vec3(x, y, z)
                            Command.SINGLE_SUCCESS
                        })
                )
            )
        }

        is Float -> {
            this as Vec3Conf<Float>
            id.then(
                argument<Any, Float>(
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
                    argument<Any, Float>(
                        "y", if (this.rangeY != null) {
                            FloatArgumentType.floatArg(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            FloatArgumentType.floatArg()
                        }
                    ).then(
                        argument<Any, Float>(
                            "z", if (this.rangeZ != null) {
                                FloatArgumentType.floatArg(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                FloatArgumentType.floatArg()
                            }
                        ).executes { context ->
                            val x = FloatArgumentType.getFloat(context, "x")
                            val y = FloatArgumentType.getFloat(context, "y")
                            val z = FloatArgumentType.getFloat(context, "z")
                            this.value = Vec3(x, y, z)
                            Command.SINGLE_SUCCESS
                        })
                )
            )
        }

        is Double -> {
            this as Vec3Conf<Double>
            id.then(
                argument<Any, Double>(
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
                    argument<Any, Double>(
                        "y", if (this.rangeY != null) {
                            DoubleArgumentType.doubleArg(
                                this.rangeY.min,
                                this.rangeY.max
                            )
                        } else {
                            DoubleArgumentType.doubleArg()
                        }
                    ).then(
                        argument<Any, Double>(
                            "z", if (this.rangeZ != null) {
                                DoubleArgumentType.doubleArg(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                DoubleArgumentType.doubleArg()
                            }
                        ).executes { context ->
                            val x = DoubleArgumentType.getDouble(context, "x")
                            val y = DoubleArgumentType.getDouble(context, "y")
                            val z = DoubleArgumentType.getDouble(context, "z")
                            this.value = Vec3(x, y, z)
                            Command.SINGLE_SUCCESS
                        })
                )
            )
        }
    }
}
