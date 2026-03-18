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

import cn.enaium.mineconf.conf.Vec3Conf
import cn.enaium.mineconf.conf.Widget
import cn.enaium.mineconf.type.Vec3
import imgui.type.*

/**
 * @author Enaium
 */
object Vec3Widget {
    fun vec3(id: String, widget: Widget?, conf: Vec3Conf<*>) {
        run {
            val id = "X$id"
            when (conf.value.x) {
                is Long -> {
                    conf as Vec3Conf<Long>
                    val value = conf.value.x as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeX?.min
                    val max = conf.rangeX?.max
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec3(imLong.get(), conf.value.y, conf.value.z)
                    }
                }

                is Int -> {
                    conf as Vec3Conf<Int>
                    val value = conf.value.x as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeX?.min
                    val max = conf.rangeX?.max
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec3(imInt.get(), conf.value.y, conf.value.z)
                    }
                }

                is Short -> {
                    conf as Vec3Conf<Short>
                    val value = conf.value.x as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec3(imShort.get(), conf.value.y, conf.value.z)
                    }
                }

                is Byte -> {
                    conf as Vec3Conf<Byte>
                    val value = conf.value.x as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec3(imShort.get().toByte(), conf.value.y, conf.value.z)
                    }
                }

                is Float -> {
                    conf as Vec3Conf<Float>
                    val value = conf.value.x as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec3(imFloat.get(), conf.value.y, conf.value.z)
                    }
                }

                is Double -> {
                    conf as Vec3Conf<Double>
                    val value = conf.value.x as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec3(imDouble.get(), conf.value.y, conf.value.z)
                    }
                }
            }
        }
        run {
            val id = "Y$id"
            when (conf.value.y) {
                is Long -> {
                    conf as Vec3Conf<Long>
                    val value = conf.value.y as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec3(conf.value.x, imLong.get(), conf.value.z)
                    }
                }

                is Int -> {
                    conf as Vec3Conf<Int>
                    val value = conf.value.y as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec3(conf.value.x, imInt.get(), conf.value.z)
                    }
                }

                is Short -> {
                    conf as Vec3Conf<Short>
                    val value = conf.value.y as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec3(conf.value.x, imShort.get(), conf.value.z)
                    }
                }

                is Byte -> {
                    conf as Vec3Conf<Byte>
                    val value = conf.value.y as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec3(conf.value.x, imShort.get().toByte(), conf.value.z)
                    }
                }

                is Float -> {
                    conf as Vec3Conf<Float>
                    val value = conf.value.y as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec3(conf.value.x, imFloat.get(), conf.value.z)
                    }
                }

                is Double -> {
                    conf as Vec3Conf<Double>
                    val value = conf.value.y as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec3(conf.value.x, imDouble.get(), conf.value.z)
                    }
                }
            }
        }
        run {
            val id = "Z$id"
            when (conf.value.z) {
                is Long -> {
                    conf as Vec3Conf<Long>
                    val value = conf.value.z as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeZ?.min
                    val max = conf.rangeZ?.max
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec3(conf.value.x, conf.value.y, imLong.get())
                    }
                }

                is Int -> {
                    conf as Vec3Conf<Int>
                    val value = conf.value.z as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeZ?.min
                    val max = conf.rangeZ?.max
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec3(conf.value.x, conf.value.y, imInt.get())
                    }
                }

                is Short -> {
                    conf as Vec3Conf<Short>
                    val value = conf.value.z as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeZ?.min
                    val max = conf.rangeZ?.max
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec3(conf.value.x, conf.value.y, imShort.get())
                    }
                }

                is Byte -> {
                    conf as Vec3Conf<Byte>
                    val value = conf.value.z as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeZ?.min ?: 0
                    val max = conf.rangeZ?.max ?: 0
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec3(conf.value.x, conf.value.y, imShort.get().toByte())
                    }
                }

                is Float -> {
                    conf as Vec3Conf<Float>
                    val value = conf.value.z as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeZ?.min ?: 0
                    val max = conf.rangeZ?.max ?: 0
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec3(conf.value.x, conf.value.y, imFloat.get())
                    }
                }

                is Double -> {
                    conf as Vec3Conf<Double>
                    val value = conf.value.z as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeZ?.min ?: 0
                    val max = conf.rangeZ?.max ?: 0
                    val step = conf.stepZ

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec3(conf.value.x, conf.value.y, imDouble.get())
                    }
                }
            }
        }
    }
}