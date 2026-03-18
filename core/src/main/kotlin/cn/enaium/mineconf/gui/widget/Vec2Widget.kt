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

import cn.enaium.mineconf.conf.Vec2Conf
import cn.enaium.mineconf.conf.Widget
import cn.enaium.mineconf.type.Vec2
import imgui.type.*

/**
 * @author Enaium
 */
object Vec2Widget {
    fun vec2(id: String, widget: Widget?, conf: Vec2Conf<*>) {
        run {
            val id = "X$id"
            when (conf.value.x) {
                is Long -> {
                    conf as Vec2Conf<Long>
                    val value = conf.value.x as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeX?.min
                    val max = conf.rangeX?.max
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec2(imLong.get(), conf.value.y);
                    }
                }

                is Int -> {
                    conf as Vec2Conf<Int>
                    val value = conf.value.x as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeX?.min
                    val max = conf.rangeX?.max
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec2(imInt.get(), conf.value.y)
                    }
                }

                is Short -> {
                    conf as Vec2Conf<Short>
                    val value = conf.value.x as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec2(imShort.get(), conf.value.y)
                    }
                }

                is Byte -> {
                    conf as Vec2Conf<Byte>
                    val value = conf.value.x as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec2(imShort.get().toByte(), conf.value.y)
                    }
                }

                is Float -> {
                    conf as Vec2Conf<Float>
                    val value = conf.value.x as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec2(imFloat.get(), conf.value.y)
                    }
                }

                is Double -> {
                    conf as Vec2Conf<Double>
                    val value = conf.value.x as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeX?.min ?: 0
                    val max = conf.rangeX?.max ?: 0
                    val step = conf.stepX

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec2(imDouble.get(), conf.value.y)
                    }
                }
            }
        }
        run {
            val id = "Y$id"
            when (conf.value.y) {
                is Long -> {
                    conf as Vec2Conf<Long>
                    val value = conf.value.y as Long? ?: 0
                    val imLong = ImLong(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imLong, min, max, step)) {
                        conf.value = Vec2(conf.value.x, imLong.get())
                    }
                }

                is Int -> {
                    conf as Vec2Conf<Int>
                    val value = conf.value.y as Int? ?: 0
                    val imInt = ImInt(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imInt, min, max, step)) {
                        conf.value = Vec2(conf.value.x, imInt.get())
                    }
                }

                is Short -> {
                    conf as Vec2Conf<Short>
                    val value = conf.value.y as Short? ?: 0
                    val imShort = ImShort(value)
                    val min = conf.rangeY?.min
                    val max = conf.rangeY?.max
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec2(conf.value.x, imShort.get())
                    }
                }

                is Byte -> {
                    conf as Vec2Conf<Byte>
                    val value = conf.value.y as Byte? ?: 0
                    val imShort = ImShort(value.toShort())
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imShort, min, max, step)) {
                        conf.value = Vec2(conf.value.x, imShort.get().toByte())
                    }
                }

                is Float -> {
                    conf as Vec2Conf<Float>
                    val value = conf.value.y as Float? ?: 0f
                    val imFloat = ImFloat(value)
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                        conf.value = Vec2(conf.value.x, imFloat.get())
                    }
                }

                is Double -> {
                    conf as Vec2Conf<Double>
                    val value = conf.value.y as Double? ?: 0.0
                    val imDouble = ImDouble(value)
                    val min = conf.rangeY?.min ?: 0
                    val max = conf.rangeY?.max ?: 0
                    val step = conf.stepY

                    if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                        conf.value = Vec2(conf.value.x, imDouble.get())
                    }
                }
            }
        }
    }
}