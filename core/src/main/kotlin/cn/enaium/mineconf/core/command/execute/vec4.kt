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
import cn.enaium.mineconf.core.common.text.Color
import cn.enaium.mineconf.core.common.text.Text
import cn.enaium.mineconf.core.conf.Vec4Conf
import cn.enaium.mineconf.core.type.Vec4
import cn.enaium.mineconf.core.utility.i18n
import cn.enaium.mineconf.core.utility.text
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
fun Vec4Conf<*>.vec4(id: LiteralArgumentBuilder<CommonSource>) {
    when (this.value.x) {
        is Long -> {
            this as Vec4Conf<Long>
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
                    ).then(
                        argument<Long>(
                            "z", if (this.rangeZ != null) {
                                LongArgumentType.longArg(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                LongArgumentType.longArg()
                            }
                        ).then(
                            argument<Long>(
                                "w", if (this.rangeW != null) {
                                    LongArgumentType.longArg(
                                        this.rangeW.min,
                                        this.rangeW.max
                                    )
                                } else {
                                    LongArgumentType.longArg()
                                }
                            ).executes { context ->
                                val x = LongArgumentType.getLong(context, "x")
                                val y = LongArgumentType.getLong(context, "y")
                                val z = LongArgumentType.getLong(context, "z")
                                val w = LongArgumentType.getLong(context, "w")
                                this.value = Vec4(x, y, z, w)
                                context.source.sendFeedback(i18n("command.set.success").text().style {
                                    color = Color.GREEN
                                })
                                Command.SINGLE_SUCCESS
                            })
                    )
                )
            )
        }

        is Int -> {
            this as Vec4Conf<Int>
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
                    ).then(
                        argument<Int>(
                            "z", if (this.rangeZ != null) {
                                IntegerArgumentType.integer(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                IntegerArgumentType.integer()
                            }
                        ).then(
                            argument<Int>(
                                "w", if (this.rangeW != null) {
                                    IntegerArgumentType.integer(
                                        this.rangeW.min,
                                        this.rangeW.max
                                    )
                                } else {
                                    IntegerArgumentType.integer()
                                }
                            ).executes { context ->
                                val x = IntegerArgumentType.getInteger(context, "x")
                                val y = IntegerArgumentType.getInteger(context, "y")
                                val z = IntegerArgumentType.getInteger(context, "z")
                                val w = IntegerArgumentType.getInteger(context, "w")
                                this.value = Vec4(x, y, z, w)
                                context.source.sendFeedback(i18n("command.set.success").text().style {
                                    color = Color.GREEN
                                })
                                Command.SINGLE_SUCCESS
                            })
                    )
                )
            )
        }

        is Short -> {
            this as Vec4Conf<Short>
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
                    ).then(
                        argument<Int>(
                            "z", if (this.rangeZ != null) {
                                IntegerArgumentType.integer(
                                    this.rangeZ.min.toInt(),
                                    this.rangeZ.max.toInt()
                                )
                            } else {
                                IntegerArgumentType.integer()
                            }
                        ).then(
                            argument<Int>(
                                "w", if (this.rangeW != null) {
                                    IntegerArgumentType.integer(
                                        this.rangeW.min.toInt(),
                                        this.rangeW.max.toInt()
                                    )
                                } else {
                                    IntegerArgumentType.integer()
                                }
                            ).executes { context ->
                                val x = IntegerArgumentType.getInteger(context, "x").toShort()
                                val y = IntegerArgumentType.getInteger(context, "y").toShort()
                                val z = IntegerArgumentType.getInteger(context, "z").toShort()
                                val w = IntegerArgumentType.getInteger(context, "w").toShort()
                                this.value = Vec4(x, y, z, w)
                                context.source.sendFeedback(i18n("command.set.success").text().style {
                                    color = Color.GREEN
                                })
                                Command.SINGLE_SUCCESS
                            })
                    )
                )
            )
        }

        is Byte -> {
            this as Vec4Conf<Byte>
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
                    ).then(
                        argument<Int>(
                            "z", if (this.rangeZ != null) {
                                IntegerArgumentType.integer(
                                    this.rangeZ.min.toInt(),
                                    this.rangeZ.max.toInt()
                                )
                            } else {
                                IntegerArgumentType.integer()
                            }
                        ).then(
                            argument<Int>(
                                "w", if (this.rangeW != null) {
                                    IntegerArgumentType.integer(
                                        this.rangeW.min.toInt(),
                                        this.rangeW.max.toInt()
                                    )
                                } else {
                                    IntegerArgumentType.integer()
                                }
                            ).executes { context ->
                                val x = IntegerArgumentType.getInteger(context, "x").toByte()
                                val y = IntegerArgumentType.getInteger(context, "y").toByte()
                                val z = IntegerArgumentType.getInteger(context, "z").toByte()
                                val w = IntegerArgumentType.getInteger(context, "w").toByte()
                                this.value = Vec4(x, y, z, w)
                                context.source.sendFeedback(i18n("command.set.success").text().style {
                                    color = Color.GREEN
                                })
                                Command.SINGLE_SUCCESS
                            })
                    )
                )
            )
        }

        is Float -> {
            this as Vec4Conf<Float>
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
                    ).then(
                        argument<Float>(
                            "z", if (this.rangeZ != null) {
                                FloatArgumentType.floatArg(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                FloatArgumentType.floatArg()
                            }
                        ).then(
                            argument<Float>(
                                "w", if (this.rangeW != null) {
                                    FloatArgumentType.floatArg(
                                        this.rangeW.min,
                                        this.rangeW.max
                                    )
                                } else {
                                    FloatArgumentType.floatArg()
                                }
                            ).executes { context ->
                                val x = FloatArgumentType.getFloat(context, "x")
                                val y = FloatArgumentType.getFloat(context, "y")
                                val z = FloatArgumentType.getFloat(context, "z")
                                val w = FloatArgumentType.getFloat(context, "w")
                                this.value = Vec4(x, y, z, w)
                                context.source.sendFeedback(i18n("command.set.success").text().style {
                                    color = Color.GREEN
                                })
                                Command.SINGLE_SUCCESS
                            })
                    )
                )
            )
        }

        is Double -> {
            this as Vec4Conf<Double>
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
                    ).then(
                        argument<Double>(
                            "z", if (this.rangeZ != null) {
                                DoubleArgumentType.doubleArg(
                                    this.rangeZ.min,
                                    this.rangeZ.max
                                )
                            } else {
                                DoubleArgumentType.doubleArg()
                            }
                        ).then(
                            argument<Double>(
                                "w", if (this.rangeW != null) {
                                    DoubleArgumentType.doubleArg(
                                        this.rangeW.min,
                                        this.rangeW.max
                                    )
                                } else {
                                    DoubleArgumentType.doubleArg()
                                }
                            ).executes { context ->
                                val x = DoubleArgumentType.getDouble(context, "x")
                                val y = DoubleArgumentType.getDouble(context, "y")
                                val z = DoubleArgumentType.getDouble(context, "z")
                                val w = DoubleArgumentType.getDouble(context, "w")
                                this.value = Vec4(x, y, z, w)
                                context.source.sendFeedback(i18n("command.set.success").text().style {
                                    color = Color.GREEN
                                })
                                Command.SINGLE_SUCCESS
                            })
                    )
                )
            )
        }
    }
}
