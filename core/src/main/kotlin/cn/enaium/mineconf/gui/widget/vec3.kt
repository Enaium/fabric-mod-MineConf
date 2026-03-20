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
import cn.enaium.mineconf.type.Vec3
import imgui.type.*

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun Vec3Conf<*>.vec3(id: String) {
    run {
        val id = "X$id"
        when (this.value.x) {
            is Long -> {
                this as Vec3Conf<Long>
                val value = this.value.x
                val imLong = ImLong(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imLong, min, max, step)) {
                    this.value = Vec3(imLong.get(), this.value.y, this.value.z)
                }
            }

            is Int -> {
                this as Vec3Conf<Int>
                val value = this.value.x
                val imInt = ImInt(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imInt, min, max, step)) {
                    this.value = Vec3(imInt.get(), this.value.y, this.value.z)
                }
            }

            is Short -> {
                this as Vec3Conf<Short>
                val value = this.value.x
                val imShort = ImShort(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec3(imShort.get(), this.value.y, this.value.z)
                }
            }

            is Byte -> {
                this as Vec3Conf<Byte>
                val value = this.value.x
                val imShort = ImShort(value.toShort())
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec3(imShort.get().toByte(), this.value.y, this.value.z)
                }
            }

            is Float -> {
                this as Vec3Conf<Float>
                val value = this.value.x
                val imFloat = ImFloat(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                    this.value = Vec3(imFloat.get(), this.value.y, this.value.z)
                }
            }

            is Double -> {
                this as Vec3Conf<Double>
                val value = this.value.x
                val imDouble = ImDouble(value)
                val min = this.rangeX?.min
                val max = this.rangeX?.max
                val step = this.stepX

                if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                    this.value = Vec3(imDouble.get(), this.value.y, this.value.z)
                }
            }
        }
    }
    run {
        val id = "Y$id"
        when (this.value.y) {
            is Long -> {
                this as Vec3Conf<Long>
                val value = this.value.y
                val imLong = ImLong(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imLong, min, max, step)) {
                    this.value = Vec3(this.value.x, imLong.get(), this.value.z)
                }
            }

            is Int -> {
                this as Vec3Conf<Int>
                val value = this.value.y
                val imInt = ImInt(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imInt, min, max, step)) {
                    this.value = Vec3(this.value.x, imInt.get(), this.value.z)
                }
            }

            is Short -> {
                this as Vec3Conf<Short>
                val value = this.value.y
                val imShort = ImShort(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec3(this.value.x, imShort.get(), this.value.z)
                }
            }

            is Byte -> {
                this as Vec3Conf<Byte>
                val value = this.value.y
                val imShort = ImShort(value.toShort())
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec3(this.value.x, imShort.get().toByte(), this.value.z)
                }
            }

            is Float -> {
                this as Vec3Conf<Float>
                val value = this.value.y
                val imFloat = ImFloat(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                    this.value = Vec3(this.value.x, imFloat.get(), this.value.z)
                }
            }

            is Double -> {
                this as Vec3Conf<Double>
                val value = this.value.y
                val imDouble = ImDouble(value)
                val min = this.rangeY?.min
                val max = this.rangeY?.max
                val step = this.stepY

                if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                    this.value = Vec3(this.value.x, imDouble.get(), this.value.z)
                }
            }
        }
    }
    run {
        val id = "Z$id"
        when (this.value.z) {
            is Long -> {
                this as Vec3Conf<Long>
                val value = this.value.z
                val imLong = ImLong(value)
                val min = this.rangeZ?.min
                val max = this.rangeZ?.max
                val step = this.stepZ

                if (InputWidget.input(id, widget, imLong, min, max, step)) {
                    this.value = Vec3(this.value.x, this.value.y, imLong.get())
                }
            }

            is Int -> {
                this as Vec3Conf<Int>
                val value = this.value.z
                val imInt = ImInt(value)
                val min = this.rangeZ?.min
                val max = this.rangeZ?.max
                val step = this.stepZ

                if (InputWidget.input(id, widget, imInt, min, max, step)) {
                    this.value = Vec3(this.value.x, this.value.y, imInt.get())
                }
            }

            is Short -> {
                this as Vec3Conf<Short>
                val value = this.value.z
                val imShort = ImShort(value)
                val min = this.rangeZ?.min
                val max = this.rangeZ?.max
                val step = this.stepZ

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec3(this.value.x, this.value.y, imShort.get())
                }
            }

            is Byte -> {
                this as Vec3Conf<Byte>
                val value = this.value.z
                val imShort = ImShort(value.toShort())
                val min = this.rangeZ?.min
                val max = this.rangeZ?.max
                val step = this.stepZ

                if (InputWidget.input(id, widget, imShort, min, max, step)) {
                    this.value = Vec3(this.value.x, this.value.y, imShort.get().toByte())
                }
            }

            is Float -> {
                this as Vec3Conf<Float>
                val value = this.value.z
                val imFloat = ImFloat(value)
                val min = this.rangeZ?.min
                val max = this.rangeZ?.max
                val step = this.stepZ

                if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                    this.value = Vec3(this.value.x, this.value.y, imFloat.get())
                }
            }

            is Double -> {
                this as Vec3Conf<Double>
                val value = this.value.z
                val imDouble = ImDouble(value)
                val min = this.rangeZ?.min
                val max = this.rangeZ?.max
                val step = this.stepZ

                if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                    this.value = Vec3(this.value.x, this.value.y, imDouble.get())
                }
            }
        }
    }
}