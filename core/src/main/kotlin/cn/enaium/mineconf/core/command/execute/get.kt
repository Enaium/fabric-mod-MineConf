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

package cn.enaium.mineconf.core.command.execute

import cn.enaium.mineconf.core.MineConfLoader
import cn.enaium.mineconf.core.command.literal
import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.core.common.text.Color
import cn.enaium.mineconf.core.common.text.HoverEvent
import cn.enaium.mineconf.core.common.text.Text
import cn.enaium.mineconf.core.conf.*
import cn.enaium.mineconf.core.utility.i18n
import cn.enaium.mineconf.core.utility.text
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST", "UPPER_BOUND_VIOLATED_IN_TYPE_OPERATOR_OR_PARAMETER_BOUNDS_WARNING")
fun get(): LiteralArgumentBuilder<CommonSource> {
    val get = literal("get")
    MineConfLoader.MINE_CONF.forEach { (modId, mineConf) ->
        mineConf.getConf().forEach { (id, conf) ->
            val modId = literal(modId).then(literal(id).executes {
                it.source.sendFeedback("----------------------------------------".text())
                it.source.sendFeedback("${i18n("command.get.id")}: ".text().style { color = Color.YELLOW; bold = true }
                    .append(conf.id.text().style { color = Color.GREEN }))
                it.source.sendFeedback(
                    "${i18n("command.get.name")}: ".text().style { color = Color.YELLOW; bold = true }
                        .append(conf.name.text().style { color = Color.GREEN })
                )
                it.source.sendFeedback("${i18n("command.get.description")}: ".text().style {
                    color = Color.YELLOW; bold = true
                }.append(conf.description.text().style { color = Color.GREEN }))
                it.source.sendFeedback("----------------------------------------".text())
                Command.SINGLE_SUCCESS
            }.then(literal("value").executes { context ->
                context.source.sendFeedback("----------------------------------------".text())
                when (conf) {
                    is NumberConf<*> -> {
                        var text = "".text()
                        text = text.append("${i18n("command.get.value")}: ".text())
                            .append("${conf.value}".text().style { color = Color.GREEN; underline = true })
                        conf.range?.also {
                            text = text.append(conf.range.text())
                        }
                        conf.step?.also {
                            text = text.append(it.step())
                        }
                        context.source.sendFeedback(text)
                    }

                    is Vec2Conf<*> -> {
                        var x = "".text().append("X: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.x}".text().style { color = Color.GREEN })
                        conf.rangeX?.also {
                            x = x.append(conf.rangeX.text())
                        }
                        conf.stepX?.also {
                            x = x.append(it.step())
                        }
                        context.source.sendFeedback(x)
                        var y = "".text().append("Y: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.y}".text().style { color = Color.GREEN })
                        conf.rangeY?.also {
                            y = y.append(conf.rangeY.text())
                        }
                        conf.stepY?.also {
                            y = y.append(it.step())
                        }
                        context.source.sendFeedback(y)
                    }

                    is Vec3Conf<*> -> {
                        var x = "".text().append("X: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.x}".text().style { color = Color.GREEN })
                        conf.rangeX?.also {
                            x = x.append(conf.rangeX.text())
                        }
                        conf.stepX?.also {
                            x = x.append(it.step())
                        }
                        context.source.sendFeedback(x)
                        var y = "".text().append("Y: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.y}".text().style { color = Color.GREEN })
                        conf.rangeY?.also {
                            y = y.append(conf.rangeY.text())
                        }
                        conf.stepY?.also {
                            y = y.append(it.step())
                        }
                        context.source.sendFeedback(y)
                        var z = "".text().append("Z: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.z}".text().style { color = Color.GREEN })
                        conf.rangeZ?.also {
                            z = z.append(conf.rangeZ.text())
                        }
                        conf.stepZ?.also {
                            z = z.append(it.step())
                        }
                        context.source.sendFeedback(z)
                    }

                    is Vec4Conf<*> -> {
                        var x = "".text().append("X: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.x}".text().style { color = Color.GREEN })
                        conf.rangeX?.also {
                            x = x.append(conf.rangeX.text())
                        }
                        conf.stepX?.also {
                            x = x.append(it.step())
                        }
                        context.source.sendFeedback(x)
                        var y = "".text().append("Y: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.y}".text().style { color = Color.GREEN })
                        conf.rangeY?.also {
                            y = y.append(conf.rangeY.text())
                        }
                        conf.stepY?.also {
                            y = y.append(it.step())
                        }
                        context.source.sendFeedback(y)
                        var z = "".text().append("Z: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.z}".text().style { color = Color.GREEN })
                        conf.rangeZ?.also {
                            z = z.append(conf.rangeZ.text())
                        }
                        conf.stepZ?.also {
                            z = z.append(it.step())
                        }
                        context.source.sendFeedback(z)
                        var w = "".text().append("W: ".text().style { color = Color.YELLOW; bold = true })
                            .append("${conf.value.w}".text().style { color = Color.GREEN })
                        conf.rangeW?.also {
                            w = w.append(conf.rangeW.text())
                        }
                        conf.stepW?.also {
                            w = w.append(it.step())
                        }
                        context.source.sendFeedback(w)
                    }

                    is OptionConf<*> -> {
                        var text = "".text()
                        text = text.append("${conf.value}".text().style { color = Color.GREEN })
                        text = text.append("(${i18n("command.get.options")}: ".text())
                        conf.options.forEach {
                            text = text.append("$it".text().style { color = Color.GREEN })
                            if (it != conf.options.last()) {
                                text = text.append(", ".text())
                            }
                        }
                        text = text.append(")".text())
                        context.source.sendFeedback(text)
                    }

                    is EnumConf<*> -> {
                        conf as EnumConf<Enum<*>>
                        var text = "".text()
                        text = text.append(conf.value.name.text().style {
                            color = Color.GREEN
                            underline = true
                            hover = HoverEvent(HoverEvent.Action.SHOW_TEXT, conf.value.toString().text())
                        })
                        text = text.append("(${i18n("command.get.enumerations")}: ".text())
                        conf.type.enumConstants.forEach {
                            text = text.append(it.name.text().style {
                                color = Color.GREEN
                                underline = true
                                hover = HoverEvent(HoverEvent.Action.SHOW_TEXT, it.toString().text())
                            })
                            if (it != conf.type.enumConstants.last()) {
                                text = text.append(", ".text())
                            }
                        }
                        text = text.append(")".text())
                        context.source.sendFeedback(text)
                    }

                    is CollectionConf<*> -> {
                        var text = "".text()
                        text = text.append(
                            "${i18n("command.get.value")}: ".text().style { color = Color.YELLOW; bold = true })
                        text = text.append("[".text())
                        conf.value.forEach {
                            text = text.append("$it".text().style { color = Color.GREEN })
                            if (it != conf.value.first()) {
                                text = text.append(", ".text())
                            }
                        }

                        text = text.append("]".text())
                        context.source.sendFeedback(text)
                    }

                    is MultimapConf<*, *> -> {
                        var text = "".text()
                        text = text.append("{".text())
                        conf.value.forEach { (o, m) ->
                            var collection = "".text()
                            collection = collection.append("$o".text().style { color = Color.YELLOW; bold = true })
                            collection = collection.append(": ".text())
                            collection = collection.append("[".text())
                            m.forEach {
                                collection = collection.append("$it".text().style { color = Color.GREEN })
                                if (it != m.last()) {
                                    collection = collection.append(", ".text())
                                }
                            }
                            collection = collection.append("]".text())
                            collection = collection.append(", ".text().style {
                                color = Color.RED
                            })
                            text = text.append(collection)
                        }
                        text = text.append("}".text())
                        context.source.sendFeedback(text)
                    }

                    else -> {
                        context.source.sendFeedback(
                            "${i18n("command.get.value")}: ".text()
                                .append("${conf.value}".text().style { color = Color.GREEN; underline = true })
                        )
                    }
                }
                context.source.sendFeedback("----------------------------------------".text())
                Command.SINGLE_SUCCESS
            }))
            get.then(modId)
        }
    }
    return get
}

private fun Range<*>.text(): Text {
    return "".text().append("(".text())
        .append("${i18n("command.get.range")}: ".text().style { color = Color.YELLOW; bold = true })
        .append("$min".text().style { color = Color.BLUE; bold = true }).append("~".text())
        .append("$max".text().style { color = Color.BLUE; bold = true })
        .append(")".text())
}

private fun Number.step(): Text {
    return "".text().append("(".text())
        .append("${i18n("command.get.step")}: ".text().style { color = Color.YELLOW; bold = true })
        .append("$this".text().style { color = Color.BLUE; bold = true }).append(")".text())
}