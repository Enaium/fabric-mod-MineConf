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

package cn.enaium.mineconf.gui.widget

import cn.enaium.mineconf.conf.Vec4Conf
import cn.enaium.mineconf.conf.Widget
import cn.enaium.mineconf.type.Vec4
import imgui.type.*

/**
 * @author Enaium
 */
object Vec4Widget {
    fun vec4(id: String, widget: Widget?, conf: Vec4Conf<*>) {
        run {
            val id = "X$id"
            when (conf.value.x) {
                is Long -> {
                    conf as Vec4Conf<Long>
                    val value = conf.value.x as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeX?.min
                    val max = conf.rangeX?.max
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec4(imLong.get(), conf.value.y, conf.value.z, conf.value.w)
                    }
                }

                is Int -> {
                    conf as Vec4Conf<Int>
                    val value = conf.value.x as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeX?.min
                    val max = conf.rangeX?.max
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec4(imInt.get(), conf.value.y, conf.value.z, conf.value.w)
                    }
                }

                is Short -> {
                    conf as Vec4Conf<Short>
                    val value = conf.value.x as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(imShort.get(), conf.value.y, conf.value.z, conf.value.w)
                    }
                }

                is Byte -> {
                    conf as Vec4Conf<Byte>
                    val value = conf.value.x as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(imShort.get().toByte(), conf.value.y, conf.value.z, conf.value.w)
                    }
                }

                is Float -> {
                    conf as Vec4Conf<Float>
                    val value = conf.value.x as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec4(imFloat.get(), conf.value.y, conf.value.z, conf.value.w)
                    }
                }

                is Double -> {
                    conf as Vec4Conf<Double>
                    val value = conf.value.x as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec4(imDouble.get(), conf.value.y, conf.value.z, conf.value.w)
                    }
                }
            }
        }
        run {
            val id = "Y$id"
            when (conf.value.y) {
                is Long -> {
                    conf as Vec4Conf<Long>
                    val value = conf.value.y as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec4(conf.value.x, imLong.get(), conf.value.z, conf.value.w)
                    }
                }

                is Int -> {
                    conf as Vec4Conf<Int>
                    val value = conf.value.y as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec4(conf.value.x, imInt.get(), conf.value.z, conf.value.w)
                    }
                }

                is Short -> {
                    conf as Vec4Conf<Short>
                    val value = conf.value.y as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(conf.value.x, imShort.get(), conf.value.z, conf.value.w)
                    }
                }

                is Byte -> {
                    conf as Vec4Conf<Byte>
                    val value = conf.value.y as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(conf.value.x, imShort.get().toByte(), conf.value.z, conf.value.w)
                    }
                }

                is Float -> {
                    conf as Vec4Conf<Float>
                    val value = conf.value.y as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec4(conf.value.x, imFloat.get(), conf.value.z, conf.value.w)
                    }
                }

                is Double -> {
                    conf as Vec4Conf<Double>
                    val value = conf.value.y as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec4(conf.value.x, imDouble.get(), conf.value.z, conf.value.w)
                    }
                }
            }
        }
        run {
            val id = "Z$id"
            when (conf.value.z) {
                is Long -> {
                    conf as Vec4Conf<Long>
                    val value = conf.value.z as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeZ?.min
                    val max = conf.rangeZ?.max
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, imLong.get(), conf.value.w)
                    }
                }

                is Int -> {
                    conf as Vec4Conf<Int>
                    val value = conf.value.z as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeZ?.min
                    val max = conf.rangeZ?.max
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, imInt.get(), conf.value.w)
                    }
                }

                is Short -> {
                    conf as Vec4Conf<Short>
                    val value = conf.value.z as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeZ?.min
                    val max = conf.rangeZ?.max
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, imShort.get(), conf.value.w)
                    }
                }

                is Byte -> {
                    conf as Vec4Conf<Byte>
                    val value = conf.value.z as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeZ?.min ?: 0
                    val max = conf.rangeZ?.max ?: 0
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, imShort.get().toByte(), conf.value.w)
                    }
                }

                is Float -> {
                    conf as Vec4Conf<Float>
                    val value = conf.value.z as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeZ?.min ?: 0
                    val max = conf.rangeZ?.max ?: 0
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, imFloat.get(), conf.value.w)
                    }
                }

                is Double -> {
                    conf as Vec4Conf<Double>
                    val value = conf.value.z as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeZ?.min ?: 0
                    val max = conf.rangeZ?.max ?: 0
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, imDouble.get(), conf.value.w)
                    }
                }
            }
        }
        run {
            val id = "W$id"
            when (conf.value.w) {
                is Long -> {
                    conf as Vec4Conf<Long>
                    val value = conf.value.w as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeW?.min
                    val max = conf.rangeW?.max
                    val step = conf.stepW

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, conf.value.z, imLong.get())
                    }
                }

                is Int -> {
                    conf as Vec4Conf<Int>
                    val value = conf.value.w as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeW?.min
                    val max = conf.rangeW?.max
                    val step = conf.stepW

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, conf.value.z, imInt.get())
                    }
                }

                is Short -> {
                    conf as Vec4Conf<Short>
                    val value = conf.value.w as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeW?.min
                    val max = conf.rangeW?.max
                    val step = conf.stepW

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, conf.value.z, imShort.get())
                    }
                }

                is Byte -> {
                    conf as Vec4Conf<Byte>
                    val value = conf.value.w as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeW?.min ?: 0
                    val max = conf.rangeW?.max ?: 0
                    val step = conf.stepW

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, conf.value.z, imShort.get().toByte())
                    }
                }

                is Float -> {
                    conf as Vec4Conf<Float>
                    val value = conf.value.w as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeW?.min ?: 0
                    val max = conf.rangeW?.max ?: 0
                    val step = conf.stepW

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, conf.value.z, imFloat.get())
                    }
                }

                is Double -> {
                    conf as Vec4Conf<Double>
                    val value = conf.value.w as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeW?.min ?: 0
                    val max = conf.rangeW?.max ?: 0
                    val step = conf.stepW

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec4(conf.value.x, conf.value.y, conf.value.z, imDouble.get())
                    }
                }
            }
        }
    }
}