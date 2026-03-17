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
package cn.enaium.mineconf

import cn.enaium.mineconf.conf.*
import cn.enaium.mineconf.type.Vec2
import cn.enaium.mineconf.type.Vec3
import cn.enaium.mineconf.type.Vec4
import java.util.function.Consumer

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
object ConfBuilder {
    @JvmStatic
    fun create(): Builder {
        return Builder()
    }

    class Builder {
        private lateinit var id: String
        private lateinit var name: String
        private lateinit var description: String
        private var widget: Widget? = null

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun widget(widget: Widget?): Builder {
            this.widget = widget
            return this
        }

        fun <T> literal(): LiteralBuilder<T> {
            return LiteralBuilder()
        }

        inner class LiteralBuilder<T> {
            fun build(defaultValue: T): Conf<T> {
                return Conf(id, name, description, defaultValue, widget)
            }
        }

        class RangeBuilder<T : Number?> {
            private var min: T? = null
            private var max: T? = null

            fun min(min: T): RangeBuilder<T> {
                this.min = min
                return this
            }

            fun max(max: T): RangeBuilder<T> {
                this.max = max
                return this
            }

            fun build(): Range<T> {
                requireNotNull(min)
                requireNotNull(max)
                return Range(min!!, max!!)
            }
        }

        fun <T : Number?> number(): NumberBuilder<T> {
            return NumberBuilder()
        }

        inner class NumberBuilder<T : Number?> {
            var range: Range<T>? = null
            var step: T? = null

            fun range(range: Consumer<RangeBuilder<T>>): NumberBuilder<T> {
                val rangeBuilder = RangeBuilder<T>()
                range.accept(rangeBuilder)
                this.range = rangeBuilder.build()
                return this
            }

            fun step(step: T): NumberBuilder<T> {
                this.step = step
                return this
            }

            fun build(defaultValue: T): NumberConf<T> {
                return NumberConf(id, name, description, defaultValue, widget, range as Range<T>, step as T)
            }
        }

        fun <T : Number> vec2(): Vec2Builder<T> {
            return Vec2Builder()
        }

        inner class Vec2Builder<T : Number> {
            private var xRange: Range<T>? = null
            private var yRange: Range<T>? = null

            fun xRange(xRange: Consumer<RangeBuilder<T>>): Vec2Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                xRange.accept(rangeBuilder)
                this.xRange = rangeBuilder.build()
                return this
            }

            fun yRange(yRange: Consumer<RangeBuilder<T>>): Vec2Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                yRange.accept(rangeBuilder)
                this.yRange = rangeBuilder.build()
                return this
            }

            fun build(defaultValueX: T, defaultValueY: T): Vec2Conf<T> {
                return Vec2Conf(
                    id,
                    name,
                    description,
                    Vec2(defaultValueX, defaultValueY),
                    widget,
                    xRange,
                    yRange
                )
            }
        }

        fun <T : Number> vec3(): Vec3Builder<T> {
            return Vec3Builder()
        }

        inner class Vec3Builder<T : Number> {
            private var xRange: Range<T>? = null
            private var yRange: Range<T>? = null
            private var zRange: Range<T>? = null

            fun xRange(xRange: Consumer<RangeBuilder<T>>): Vec3Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                xRange.accept(rangeBuilder)
                this.xRange = rangeBuilder.build()
                return this
            }

            fun yRange(yRange: Consumer<RangeBuilder<T>>): Vec3Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                yRange.accept(rangeBuilder)
                this.yRange = rangeBuilder.build()
                return this
            }

            fun zRange(zRange: Consumer<RangeBuilder<T>>): Vec3Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                zRange.accept(rangeBuilder)
                this.zRange = rangeBuilder.build()
                return this
            }

            fun build(defaultValueX: T, defaultValueY: T, defaultValueZ: T): Vec3Conf<T> {
                return Vec3Conf(
                    id,
                    name,
                    description,
                    Vec3(defaultValueX, defaultValueY, defaultValueZ),
                    widget,
                    xRange,
                    yRange,
                    zRange
                )
            }
        }

        fun <T : Number> vec4(): Vec4Builder<T> {
            return Vec4Builder()
        }

        inner class Vec4Builder<T : Number> {
            private var xRange: Range<T>? = null
            private var yRange: Range<T>? = null
            private var zRange: Range<T>? = null
            private var wRange: Range<T>? = null

            fun xRange(xRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                xRange.accept(rangeBuilder)
                this.xRange = rangeBuilder.build()
                return this
            }

            fun yRange(yRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                yRange.accept(rangeBuilder)
                this.yRange = rangeBuilder.build()
                return this
            }

            fun zRange(zRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                zRange.accept(rangeBuilder)
                this.zRange = rangeBuilder.build()
                return this
            }

            fun wRange(wRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                wRange.accept(rangeBuilder)
                this.wRange = rangeBuilder.build()
                return this
            }

            fun build(
                defaultValueX: T,
                defaultValueY: T,
                defaultValueZ: T,
                defaultValueW: T
            ): Vec4Conf<T> {
                return Vec4Conf(
                    id,
                    name,
                    description,
                    Vec4(defaultValueX, defaultValueY, defaultValueZ, defaultValueW),
                    widget,
                    xRange,
                    yRange,
                    zRange,
                    wRange
                )
            }
        }

        fun <T> option(): OptionBuilder<T> {
            return OptionBuilder()
        }

        inner class OptionBuilder<T> {
            private var options: MutableList<T>? = null

            fun options(options: MutableList<T>?): OptionBuilder<T> {
                this.options = options
                return this
            }

            fun <T> build(defaultValue: T): OptionConf<T> {
                return OptionConf(
                    id,
                    name,
                    description,
                    defaultValue,
                    widget,
                    options as MutableCollection<T>
                )
            }
        }

        fun <T> collection(): CollectionBuilder<T> {
            return CollectionBuilder()
        }

        inner class CollectionBuilder<T> {
            fun <T> build(defaultValue: MutableCollection<T>): CollectionConf<T> {
                return CollectionConf(id, name, description, defaultValue, widget)
            }
        }

        fun <O, M> multimap(): MultimapBuilder<O, M> {
            return MultimapBuilder()
        }

        inner class MultimapBuilder<O, M> {
            fun build(defaultValue: MutableMap<O, out MutableCollection<M>>): MultimapConf<O, M> {
                return MultimapConf(id, name, description, defaultValue, widget)
            }
        }
    }
}
