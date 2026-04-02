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
                it.source.sendFeedback("${i18n("command.get.id")}:".text().style { color = Color.YELLOW; bold = true }
                    .append(conf.id.text().style { color = Color.GREEN }))
                it.source.sendFeedback("${i18n("command.get.name")}:".text().style { color = Color.YELLOW; bold = true }
                    .append(conf.name.text().style { color = Color.GREEN }))
                it.source.sendFeedback("${i18n("command.get.description")}:".text().style {
                    color = Color.YELLOW; bold = true
                }.append(conf.description.text().style { color = Color.GREEN }))
                it.source.sendFeedback("----------------------------------------".text())
                Command.SINGLE_SUCCESS
            }.then(literal("value").executes { context ->
                context.source.sendFeedback("----------------------------------------".text())
                when (conf) {
                    is NumberConf<*> -> {
                        var text = "${conf.value}".text()
                        conf.range?.also {
                            text =
                                text.append("(${i18n("command.get.range")}: ${conf.range.min}~${conf.range.max})".text())
                        }
                        conf.step?.also {
                            text = text.append("(${i18n("command.get.step")}: ${conf.step})".text())
                        }
                        context.source.sendFeedback(text)
                    }

                    is Vec2Conf<*> -> {
                        var x = "X:${conf.value.x}".text()
                        conf.rangeX?.also {
                            x = x.append("(${i18n("command.get.range")}: ${conf.rangeX.min}~${conf.rangeX.max})".text())
                        }
                        conf.stepX?.also {
                            x = x.append("(${i18n("command.get.step")}: ${conf.stepX})".text())
                        }
                        context.source.sendFeedback(x)
                        var y = "Y:${conf.value.y}".text()
                        conf.rangeY?.also {
                            y = y.append("(${i18n("command.get.range")}: ${conf.rangeY.min}~${conf.rangeY.max})".text())
                        }
                        conf.stepY?.also {
                            y = y.append("(${i18n("command.get.step")}: ${conf.stepY})".text())
                        }
                        context.source.sendFeedback(y)
                    }

                    is Vec3Conf<*> -> {
                        var x = "${conf.value.x}".text()
                        conf.rangeX?.also {
                            x = x.append("(${i18n("command.get.range")}: ${conf.rangeX.min}~${conf.rangeX.max})".text())
                        }
                        conf.stepX?.also {
                            x = x.append("(${i18n("command.get.step")}: ${conf.stepX})".text())
                        }
                        context.source.sendFeedback(x)
                        var y = "${conf.value.y}".text()
                        conf.rangeY?.also {
                            y = y.append("(${i18n("command.get.range")}: ${conf.rangeY.min}~${conf.rangeY.max})".text())
                        }
                        conf.stepY?.also {
                            y = y.append("(${i18n("command.get.step")}: ${conf.stepY})".text())
                        }
                        context.source.sendFeedback(y)
                        var z = "${conf.value.z}".text()
                        conf.rangeZ?.also {
                            z = z.append("(${i18n("command.get.range")}: ${conf.rangeZ.min}~${conf.rangeZ.max})".text())
                        }
                        conf.stepZ?.also {
                            z = z.append("(${i18n("command.get.step")}: ${conf.stepZ})".text())
                        }
                        context.source.sendFeedback(y)
                    }

                    is Vec4Conf<*> -> {
                        var x = "${conf.value.x}".text()
                        conf.rangeX?.also {
                            x = x.append("(${i18n("command.get.range")}: ${conf.rangeX.min}~${conf.rangeX.max})".text())
                        }
                        conf.stepX?.also {
                            x = x.append("(${i18n("command.get.step")}: ${conf.stepX})".text())
                        }
                        context.source.sendFeedback(x)
                        val y = "${conf.value.y}".text()
                        conf.rangeY?.also {
                            y.append("(${i18n("command.get.range")}: ${conf.rangeY.min}~${conf.rangeY.max})".text())
                        }
                        conf.stepY?.also {
                            y.append("(${i18n("command.get.step")}: ${conf.stepY})".text())
                        }
                        context.source.sendFeedback(y)
                        val z = "${conf.value.z}".text()
                        conf.rangeZ?.also {
                            z.append("(${i18n("command.get.range")}: ${conf.rangeZ.min}~${conf.rangeZ.max})".text())
                        }
                        conf.stepZ?.also {
                            z.append("(${i18n("command.get.step")}: ${conf.stepZ})".text())
                        }
                        context.source.sendFeedback(z)
                        var w = "${conf.value.w}".text()
                        conf.rangeW?.also {
                            w = w.append("(${i18n("command.get.range")}: ${conf.rangeW.min}~${conf.rangeW.max})".text())
                        }
                        conf.stepW?.also {
                            w = w.append("(${i18n("command.get.step")}: ${conf.stepW})".text())
                        }
                        context.source.sendFeedback(w)
                    }

                    is OptionConf<*> -> {
                        var text = "${conf.value}".text()

                        text = text.append("(${i18n("command.get.options")}: ".text())
                        conf.options.forEach {
                            text = text.append("$it".text())
                            if (it != conf.options.last()) {
                                text = text.append(", ".text())
                            }
                        }
                        text = text.append(")".text())
                        context.source.sendFeedback(text)
                    }

                    is EnumConf<*> -> {
                        conf as EnumConf<Enum<*>>
                        var text = conf.value.name.text().style {
                            underline = true
                            hover = HoverEvent(HoverEvent.Action.SHOW_TEXT, conf.value.toString().text())
                        }
                        text = text.append("(${i18n("command.get.enumerations")}: ".text())
                        conf.type.enumConstants.forEach {
                            text = text.append(it.name.text().style {
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

                    else -> {
                        context.source.sendFeedback(conf.valueString().text())
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