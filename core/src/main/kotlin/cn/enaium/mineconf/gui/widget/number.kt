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

import cn.enaium.mineconf.conf.NumberConf
import imgui.type.*

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun NumberConf<*>.number(id: String) {
    when (this.value) {
        is Long -> {
            this as NumberConf<Long>
            val value = this.value
            val imLong = ImLong(value)
            val min = this.range?.min
            val max = this.range?.max
            val step = this.step

            if (InputWidget.input(id, widget, imLong, min, max, step)) {
                this.value = imLong.get()
            }
        }

        is Int -> {
            this as NumberConf<Int>
            val value = this.value
            val imInt = ImInt(value)
            val min = this.range?.min
            val max = this.range?.max
            val step = this.step

            if (InputWidget.input(id, widget, imInt, min, max, step)) {
                this.value = imInt.get()
            }
        }

        is Short -> {
            this as NumberConf<Short>
            val value = this.value
            val imShort = ImShort(value)
            val min = this.range?.min
            val max = this.range?.max
            val step = this.step

            if (InputWidget.input(id, widget, imShort, min, max, step)) {
                this.value = imShort.get()
            }
        }

        is Byte -> {
            this as NumberConf<Byte>
            val value = this.value
            val imShort = ImShort(value.toShort())
            val min = this.range?.min
            val max = this.range?.max
            val step = this.step

            if (InputWidget.input(id, widget, imShort, min, max, step)) {
                this.value = imShort.get().toByte()
            }
        }

        is Float -> {
            this as NumberConf<Float>
            val value = this.value
            val imFloat = ImFloat(value)
            val min = this.range?.min
            val max = this.range?.max
            val step = this.step

            if (InputWidget.input(id, widget, imFloat, min, max, step)) {
                this.value = imFloat.get()
            }
        }

        is Double -> {
            this as NumberConf<Double>
            val value = this.value
            val imDouble = ImDouble(value)
            val min = this.range?.min
            val max = this.range?.max
            val step = this.step

            if (InputWidget.input(id, widget, imDouble, min, max, step)) {
                this.value = imDouble.get()
            }
        }
    }
}