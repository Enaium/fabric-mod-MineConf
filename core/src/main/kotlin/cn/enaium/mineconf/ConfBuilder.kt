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
            private var range: Range<T>? = null
            private var step: T? = null

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
            private var xStep: T? = null
            private var yStep: T? = null

            fun rangeX(xRange: Consumer<RangeBuilder<T>>): Vec2Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                xRange.accept(rangeBuilder)
                this.xRange = rangeBuilder.build()
                return this
            }

            fun rangeY(yRange: Consumer<RangeBuilder<T>>): Vec2Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                yRange.accept(rangeBuilder)
                this.yRange = rangeBuilder.build()
                return this
            }

            fun stepX(xStep: T): Vec2Builder<T> {
                this.xStep = xStep
                return this
            }

            fun stepY(yStep: T): Vec2Builder<T> {
                this.yStep = yStep
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
                    yRange,
                    xStep,
                    yStep
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
            private var xStep: T? = null
            private var yStep: T? = null
            private var zStep: T? = null

            fun rangeX(xRange: Consumer<RangeBuilder<T>>): Vec3Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                xRange.accept(rangeBuilder)
                this.xRange = rangeBuilder.build()
                return this
            }

            fun rangeY(yRange: Consumer<RangeBuilder<T>>): Vec3Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                yRange.accept(rangeBuilder)
                this.yRange = rangeBuilder.build()
                return this
            }

            fun rangeZ(zRange: Consumer<RangeBuilder<T>>): Vec3Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                zRange.accept(rangeBuilder)
                this.zRange = rangeBuilder.build()
                return this
            }

            fun stepX(xStep: T): Vec3Builder<T> {
                this.xStep = xStep
                return this
            }

            fun stepY(yStep: T): Vec3Builder<T> {
                this.yStep = yStep
                return this
            }

            fun stepZ(yStep: T): Vec3Builder<T> {
                this.yStep = yStep
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
                    zRange,
                    xStep,
                    yStep,
                    zStep
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
            private var xStep: T? = null
            private var yStep: T? = null
            private var zStep: T? = null
            private var wStep: T? = null

            fun rangeX(xRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                xRange.accept(rangeBuilder)
                this.xRange = rangeBuilder.build()
                return this
            }

            fun rangeY(yRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                yRange.accept(rangeBuilder)
                this.yRange = rangeBuilder.build()
                return this
            }

            fun rangeZ(zRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                zRange.accept(rangeBuilder)
                this.zRange = rangeBuilder.build()
                return this
            }

            fun rangeW(wRange: Consumer<RangeBuilder<T>>): Vec4Builder<T> {
                val rangeBuilder = RangeBuilder<T>()
                wRange.accept(rangeBuilder)
                this.wRange = rangeBuilder.build()
                return this
            }

            fun stepX(xStep: T): Vec4Builder<T> {
                this.xStep = xStep
                return this
            }

            fun stepY(yStep: T): Vec4Builder<T> {
                this.yStep = yStep
                return this
            }

            fun stepZ(yStep: T): Vec4Builder<T> {
                this.yStep = yStep
                return this
            }

            fun stepW(yStep: T): Vec4Builder<T> {
                this.yStep = yStep
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
                    wRange,
                    xStep,
                    yStep,
                    zStep,
                    wStep
                )
            }
        }

        fun <T> option(): OptionBuilder<T> {
            return OptionBuilder()
        }

        inner class OptionBuilder<T> {
            private lateinit var options: Collection<T>

            fun options(options: Collection<T>): OptionBuilder<T> {
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
                    options
                ) as OptionConf<T>
            }
        }

        fun <T : Enum<T>> enumeration(): EnumBuilder<T> {
            return EnumBuilder()
        }

        inner class EnumBuilder<T : Enum<T>> {
            private lateinit var type: Class<T>

            fun type(type: Class<T>): EnumBuilder<T> {
                this.type = type
                return this
            }

            fun build(defaultValue: T): EnumConf<T> {
                return EnumConf(
                    id,
                    name,
                    description,
                    defaultValue,
                    widget,
                    type
                )
            }
        }

        fun <T> collection(): CollectionBuilder<T> {
            return CollectionBuilder()
        }

        inner class CollectionBuilder<T> {
            private var options: Collection<T>? = null
            private var converter: (String) -> T = { it as T }

            fun options(options: Collection<T>): CollectionBuilder<T> {
                this.options = options
                return this
            }

            fun converter(converter: (String) -> T): CollectionBuilder<T> {
                this.converter = converter
                return this
            }

            fun build(defaultValue: MutableCollection<T>): CollectionConf<T> {
                return CollectionConf(
                    id,
                    name,
                    description,
                    defaultValue,
                    widget,
                    converter,
                    options
                )
            }
        }

        fun <O, M> multimap(): MultimapBuilder<O, M> {
            return MultimapBuilder()
        }

        inner class MultimapBuilder<O, M> {
            private var keyOptions: Collection<O>? = null
            private var keyConverter: (String) -> O = { it as O }
            private var valueOptions: Collection<M>? = null
            private var valueConverter: (String) -> M = { it as M }

            fun keyOptions(options: Collection<O>): MultimapBuilder<O, M> {
                this.keyOptions = options
                return this
            }

            fun keyConverter(keyConverter: (String) -> O): MultimapBuilder<O, M> {
                this.keyConverter = keyConverter
                return this
            }

            fun valueOptions(options: Collection<O>): MultimapBuilder<O, M> {
                this.keyOptions = options
                return this
            }

            fun valueConverter(valueConverter: (String) -> M): MultimapBuilder<O, M> {
                this.valueConverter = valueConverter
                return this
            }

            fun build(defaultValue: MutableMap<O, Collection<M>>): MultimapConf<O, M> {
                return MultimapConf(
                    id,
                    name,
                    description,
                    defaultValue,
                    widget,
                    keyConverter,
                    keyOptions,
                    valueConverter,
                    valueOptions
                )
            }
        }
    }
}
