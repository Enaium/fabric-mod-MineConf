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

package cn.enaium.mineconf.core.gui.widget

import cn.enaium.mineconf.core.conf.Vec4Conf
import cn.enaium.mineconf.core.conf.Widget
import cn.enaium.mineconf.core.type.Vec4
import imgui.type.*

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun Vec4Conf<*>.vec4(id: String) {
    when (widget) {
        Widget.COLOR_EDIT -> this.color4(id)
        Widget.COLOR_PICKER -> this.color4(id)
        else -> {
            run {
                val id = "X$id"
                when (this.value.x) {
                    is Long -> {
                        this as Vec4Conf<Long>
                        val value = this.value.x
                        val imLong = ImLong(value)
                        val min = this.rangeX?.min
                        val max = this.rangeX?.max
                        val step = this.stepX

                        if (InputWidget.input(id, widget, imLong, min, max, step)) {
                            this.value = Vec4(imLong.get(), this.value.y, this.value.z, this.value.w)
                        }
                    }

                    is Int -> {
                        this as Vec4Conf<Int>
                        val value = this.value.x
                        val imInt = ImInt(value)
                        val min = this.rangeX?.min
                        val max = this.rangeX?.max
                        val step = this.stepX

                        if (InputWidget.input(id, widget, imInt, min, max, step)) {
                            this.value = Vec4(imInt.get(), this.value.y, this.value.z, this.value.w)
                        }
                    }

                    is Short -> {
                        this as Vec4Conf<Short>
                        val value = this.value.x
                        val imShort = ImShort(value)
                        val min = this.rangeX?.min
                        val max = this.rangeX?.max
                        val step = this.stepX

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(imShort.get(), this.value.y, this.value.z, this.value.w)
                        }
                    }

                    is Byte -> {
                        this as Vec4Conf<Byte>
                        val value = this.value.x
                        val imShort = ImShort(value.toShort())
                        val min = this.rangeX?.min
                        val max = this.rangeX?.max
                        val step = this.stepX

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(imShort.get().toByte(), this.value.y, this.value.z, this.value.w)
                        }
                    }

                    is Float -> {
                        this as Vec4Conf<Float>
                        val value = this.value.x
                        val imFloat = ImFloat(value)
                        val min = this.rangeX?.min
                        val max = this.rangeX?.max
                        val step = this.stepX

                        if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                            this.value = Vec4(imFloat.get(), this.value.y, this.value.z, this.value.w)
                        }
                    }

                    is Double -> {
                        this as Vec4Conf<Double>
                        val value = this.value.x
                        val imDouble = ImDouble(value)
                        val min = this.rangeX?.min
                        val max = this.rangeX?.max
                        val step = this.stepX

                        if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                            this.value = Vec4(imDouble.get(), this.value.y, this.value.z, this.value.w)
                        }
                    }
                }
            }
            run {
                val id = "Y$id"
                when (this.value.y) {
                    is Long -> {
                        this as Vec4Conf<Long>
                        val value = this.value.y
                        val imLong = ImLong(value)
                        val min = this.rangeY?.min
                        val max = this.rangeY?.max
                        val step = this.stepY

                        if (InputWidget.input(id, widget, imLong, min, max, step)) {
                            this.value = Vec4(this.value.x, imLong.get(), this.value.z, this.value.w)
                        }
                    }

                    is Int -> {
                        this as Vec4Conf<Int>
                        val value = this.value.y
                        val imInt = ImInt(value)
                        val min = this.rangeY?.min
                        val max = this.rangeY?.max
                        val step = this.stepY

                        if (InputWidget.input(id, widget, imInt, min, max, step)) {
                            this.value = Vec4(this.value.x, imInt.get(), this.value.z, this.value.w)
                        }
                    }

                    is Short -> {
                        this as Vec4Conf<Short>
                        val value = this.value.y
                        val imShort = ImShort(value)
                        val min = this.rangeY?.min
                        val max = this.rangeY?.max
                        val step = this.stepY

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(this.value.x, imShort.get(), this.value.z, this.value.w)
                        }
                    }

                    is Byte -> {
                        this as Vec4Conf<Byte>
                        val value = this.value.y
                        val imShort = ImShort(value.toShort())
                        val min = this.rangeY?.min
                        val max = this.rangeY?.max
                        val step = this.stepY

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(this.value.x, imShort.get().toByte(), this.value.z, this.value.w)
                        }
                    }

                    is Float -> {
                        this as Vec4Conf<Float>
                        val value = this.value.y
                        val imFloat = ImFloat(value)
                        val min = this.rangeY?.min
                        val max = this.rangeY?.max
                        val step = this.stepY

                        if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                            this.value = Vec4(this.value.x, imFloat.get(), this.value.z, this.value.w)
                        }
                    }

                    is Double -> {
                        this as Vec4Conf<Double>
                        val value = this.value.y
                        val imDouble = ImDouble(value)
                        val min = this.rangeY?.min
                        val max = this.rangeY?.max
                        val step = this.stepY

                        if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                            this.value = Vec4(this.value.x, imDouble.get(), this.value.z, this.value.w)
                        }
                    }
                }
            }
            run {
                val id = "Z$id"
                when (this.value.z) {
                    is Long -> {
                        this as Vec4Conf<Long>
                        val value = this.value.z
                        val imLong = ImLong(value)
                        val min = this.rangeZ?.min
                        val max = this.rangeZ?.max
                        val step = this.stepZ

                        if (InputWidget.input(id, widget, imLong, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, imLong.get(), this.value.w)
                        }
                    }

                    is Int -> {
                        this as Vec4Conf<Int>
                        val value = this.value.z
                        val imInt = ImInt(value)
                        val min = this.rangeZ?.min
                        val max = this.rangeZ?.max
                        val step = this.stepZ

                        if (InputWidget.input(id, widget, imInt, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, imInt.get(), this.value.w)
                        }
                    }

                    is Short -> {
                        this as Vec4Conf<Short>
                        val value = this.value.z
                        val imShort = ImShort(value)
                        val min = this.rangeZ?.min
                        val max = this.rangeZ?.max
                        val step = this.stepZ

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, imShort.get(), this.value.w)
                        }
                    }

                    is Byte -> {
                        this as Vec4Conf<Byte>
                        val value = this.value.z
                        val imShort = ImShort(value.toShort())
                        val min = this.rangeZ?.min ?: 0
                        val max = this.rangeZ?.max ?: 0
                        val step = this.stepZ

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, imShort.get().toByte(), this.value.w)
                        }
                    }

                    is Float -> {
                        this as Vec4Conf<Float>
                        val value = this.value.z
                        val imFloat = ImFloat(value)
                        val min = this.rangeZ?.min ?: 0
                        val max = this.rangeZ?.max ?: 0
                        val step = this.stepZ

                        if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, imFloat.get(), this.value.w)
                        }
                    }

                    is Double -> {
                        this as Vec4Conf<Double>
                        val value = this.value.z
                        val imDouble = ImDouble(value)
                        val min = this.rangeZ?.min ?: 0
                        val max = this.rangeZ?.max ?: 0
                        val step = this.stepZ

                        if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, imDouble.get(), this.value.w)
                        }
                    }
                }
            }
            run {
                val id = "W$id"
                when (this.value.w) {
                    is Long -> {
                        this as Vec4Conf<Long>
                        val value = this.value.w
                        val imLong = ImLong(value)
                        val min = this.rangeW?.min
                        val max = this.rangeW?.max
                        val step = this.stepW

                        if (InputWidget.input(id, widget, imLong, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, this.value.z, imLong.get())
                        }
                    }

                    is Int -> {
                        this as Vec4Conf<Int>
                        val value = this.value.w
                        val imInt = ImInt(value)
                        val min = this.rangeW?.min
                        val max = this.rangeW?.max
                        val step = this.stepW

                        if (InputWidget.input(id, widget, imInt, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, this.value.z, imInt.get())
                        }
                    }

                    is Short -> {
                        this as Vec4Conf<Short>
                        val value = this.value.w
                        val imShort = ImShort(value)
                        val min = this.rangeW?.min
                        val max = this.rangeW?.max
                        val step = this.stepW

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, this.value.z, imShort.get())
                        }
                    }

                    is Byte -> {
                        this as Vec4Conf<Byte>
                        val value = this.value.w
                        val imShort = ImShort(value.toShort())
                        val min = this.rangeW?.min
                        val max = this.rangeW?.max
                        val step = this.stepW

                        if (InputWidget.input(id, widget, imShort, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, this.value.z, imShort.get().toByte())
                        }
                    }

                    is Float -> {
                        this as Vec4Conf<Float>
                        val value = this.value.w
                        val imFloat = ImFloat(value)
                        val min = this.rangeW?.min
                        val max = this.rangeW?.max
                        val step = this.stepW

                        if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, this.value.z, imFloat.get())
                        }
                    }

                    is Double -> {
                        this as Vec4Conf<Double>
                        val value = this.value.w
                        val imDouble = ImDouble(value)
                        val min = this.rangeW?.min
                        val max = this.rangeW?.max
                        val step = this.stepW

                        if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                            this.value = Vec4(this.value.x, this.value.y, this.value.z, imDouble.get())
                        }
                    }
                }
            }
        }
    }
}