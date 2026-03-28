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

import cn.enaium.mineconf.core.conf.Widget
import imgui.ImGui
import imgui.type.*

/**
 * @author Enaium
 */
object InputWidget {
    fun input(
        id: String,
        widget: Widget?,
        value: Number,
        min: Number? = null,
        max: Number? = null,
        step: Number? = null
    ): Boolean {
        when (value) {
            is ImLong -> {
                val min = min?.toLong()
                val max = max?.toLong()
                val step = step?.toLong()

                when (widget) {
                    Widget.INPUT -> {
                        if (step != null) {
                            if (ImGui.inputScalar(id, value, step)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        } else {
                            if (ImGui.inputScalar(id, value)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true

                            }
                            return false
                        }
                    }

                    Widget.DRAG -> {
                        val pData = longArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    Widget.SLIDER -> {
                        val pData = longArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.sliderScalar(id, pData, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    else -> {
                        throw UnsupportedOperationException("Unsupported widget type: $widget")
                    }
                }
            }

            is ImInt -> {
                val min = min?.toInt()
                val max = max?.toInt()
                val step = step?.toInt()

                when (widget) {
                    Widget.INPUT -> {
                        if (step != null) {
                            if (ImGui.inputScalar(id, value, step)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        } else {
                            if (ImGui.inputScalar(id, value)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true

                            }
                            return false
                        }
                    }

                    Widget.DRAG -> {
                        val pData = intArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    Widget.SLIDER -> {
                        val pData = intArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.sliderScalar(id, pData, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    else -> {
                        throw UnsupportedOperationException("Unsupported widget type: $widget")
                    }
                }
            }

            is ImShort -> {
                val min = min?.toShort()
                val max = max?.toShort()
                val step = step?.toShort()

                when (widget) {
                    Widget.INPUT -> {
                        if (step != null) {
                            if (ImGui.inputScalar(id, value, step)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        } else {
                            if (ImGui.inputScalar(id, value)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        }
                    }

                    Widget.DRAG -> {
                        val pData = shortArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    Widget.SLIDER -> {
                        val pData = shortArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.sliderScalar(id, pData, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    else -> {
                        throw UnsupportedOperationException("Unsupported widget type: $widget")
                    }
                }
            }

            is ImFloat -> {
                val min = min?.toFloat()
                val max = max?.toFloat()
                val step = step?.toFloat()

                when (widget) {
                    Widget.INPUT -> {
                        if (step != null) {
                            if (ImGui.inputScalar(id, value, step)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        } else {
                            if (ImGui.inputScalar(id, value)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        }
                    }

                    Widget.DRAG -> {
                        val pData = floatArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    Widget.SLIDER -> {
                        val pData = floatArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.sliderScalar(id, pData, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    else -> {
                        throw UnsupportedOperationException("Unsupported widget type: $widget")
                    }
                }
            }

            is ImDouble -> {
                val min = min?.toDouble()
                val max = max?.toDouble()
                val step = step?.toDouble()

                when (widget) {
                    Widget.INPUT -> {
                        if (step != null) {
                            if (ImGui.inputScalar(id, value, step)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        } else {
                            if (ImGui.inputScalar(id, value)) {
                                val get = value.get()
                                if (min != null && max != null && get !in min..max) {
                                    return false
                                }

                                value.set(get)
                                return true
                            }
                            return false
                        }
                    }

                    Widget.DRAG -> {
                        val pData = doubleArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.dragScalar(id, pData, 1f, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    Widget.SLIDER -> {
                        val pData = doubleArrayOf(value.get())
                        requireNotNull(min)
                        requireNotNull(max)
                        if (ImGui.sliderScalar(id, pData, min, max)) {
                            value.set(pData[0])
                            return true
                        }
                        return false
                    }

                    else -> {
                        throw UnsupportedOperationException("Unsupported widget type: $widget")
                    }
                }
            }
        }
        return false
    }
}