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

import cn.enaium.mineconf.conf.Conf
import cn.enaium.mineconf.conf.Widget
import imgui.ImGui
import imgui.type.*

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun Conf<*>.literal(id: String) {
    var widget = this.widget

    if (widget == null) {
        if (this.value is Number) {
            widget = Widget.INPUT
        } else if (this.value is Boolean) {
            widget = Widget.CHECKBOX
        }
    }

    when (this.value) {
        is Long -> {
            this as Conf<Long>
            val imLong = ImLong(value)
            if (InputWidget.input(id, widget, imLong)) {
                this.value = imLong.get()
            }
        }

        is Int -> {
            this as Conf<Int>
            val imInt = ImInt(value)
            if (InputWidget.input(id, widget, imInt)) {
                this.value = imInt.get()
            }
        }

        is Short -> {
            this as Conf<Short>
            val imShort = ImShort(value)
            if (InputWidget.input(id, widget, imShort)) {
                this.value = imShort.get()
            }
        }

        is Byte -> {
            this as Conf<Byte>
            val imShort = ImShort(value.toShort())
            if (InputWidget.input(id, widget, imShort)) {
                this.value = imShort.get().toByte()
            }
        }

        is Float -> {
            this as Conf<Float>
            val imFloat = ImFloat(value)
            if (InputWidget.input(id, widget, imFloat)) {
                this.value = imFloat.get()
            }
        }

        is Double -> {
            this as Conf<Double>
            val imDouble = ImDouble(value)
            if (InputWidget.input(id, widget, imDouble)) {
                this.value = imDouble.get()
            }
        }

        is String -> {
            this as Conf<String>
            val value = this.value
            val imString = ImString(value, 300)
            if (ImGui.inputText(id, imString)) {
                this.value = imString.get()
            }
        }

        is Boolean -> {
            this as Conf<Boolean>
            when (widget) {
                Widget.RADIO -> {
                    if (ImGui.radioButton(id, this.value)) {
                        this.value = !this.value
                    }
                }

                Widget.CHECKBOX -> {
                    if (ImGui.checkbox(id, this.value)) {
                        this.value = !this.value
                    }
                }

                else -> {
                    throw UnsupportedOperationException("Unsupported widget type: $widget")
                }
            }
        }
    }
}