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
import cn.enaium.mineconf.type.Vec2
import imgui.type.*

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun Vec2Conf<*>.vec2(id: String) {
    run {
        val id = "X$id"
        when (this.value.x) {
            is Long -> {
                this as Vec2Conf<Long>
                val value = this.value.x
                val imLong = ImLong(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imLong, min, max, step)) {
                    this.value = Vec2(imLong.get(), this.value.y)
                }
            }

            is Int -> {
                this as Vec2Conf<Int>
                val value = this.value.x
                val imInt = ImInt(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imInt, min, max, step)) {
                    this.value = Vec2(imInt.get(), this.value.y)
                }
            }

            is Short -> {
                this as Vec2Conf<Short>
                val value = this.value.x
                val imShort = ImShort(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec2(imShort.get(), this.value.y)
                }
            }

            is Byte -> {
                this as Vec2Conf<Byte>
                val value = this.value.x
                val imShort = ImShort(value.toShort())
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec2(imShort.get().toByte(), this.value.y)
                }
            }

            is Float -> {
                this as Vec2Conf<Float>
                val value = this.value.x
                val imFloat = ImFloat(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                    this.value = Vec2(imFloat.get(), this.value.y)
                }
            }

            is Double -> {
                this as Vec2Conf<Double>
                val value = this.value.x
                val imDouble = ImDouble(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                    this.value = Vec2(imDouble.get(), this.value.y)
                }
            }
        }
    }
    run {
        val id = "Y$id"
        when (this.value.y) {
            is Long -> {
                this as Vec2Conf<Long>
                val value = this.value.y
                val imLong = ImLong(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imLong, min, max, step)) {
                    this.value = Vec2(this.value.x, imLong.get())
                }
            }

            is Int -> {
                this as Vec2Conf<Int>
                val value = this.value.y
                val imInt = ImInt(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imInt, min, max, step)) {
                    this.value = Vec2(this.value.x, imInt.get())
                }
            }

            is Short -> {
                this as Vec2Conf<Short>
                val value = this.value.y
                val imShort = ImShort(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec2(this.value.x, imShort.get())
                }
            }

            is Byte -> {
                this as Vec2Conf<Byte>
                val value = this.value.y
                val imShort = ImShort(value.toShort())
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec2(this.value.x, imShort.get().toByte())
                }
            }

            is Float -> {
                this as Vec2Conf<Float>
                val value = this.value.y
                val imFloat = ImFloat(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                    this.value = Vec2(this.value.x, imFloat.get())
                }
            }

            is Double -> {
                this as Vec2Conf<Double>
                val value = this.value.y
                val imDouble = ImDouble(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                    this.value = Vec2(this.value.x, imDouble.get())
                }
            }
        }
    }
}