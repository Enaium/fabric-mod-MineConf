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

package cn.enaium.mineconf.command.execute

import cn.enaium.mineconf.command.argument
import cn.enaium.mineconf.common.CommonSource
import cn.enaium.mineconf.conf.Conf
import cn.enaium.mineconf.utility.i18n
import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun Conf<*>.literal(id: LiteralArgumentBuilder<CommonSource>): Boolean {
    when (this.value) {
        is Long -> {
            id.then(argument<Long>("value", LongArgumentType.longArg()).executes {
                this as Conf<Long>
                this.value = LongArgumentType.getLong(it, "value")
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        is Int -> {
            id.then(argument<Int>("value", IntegerArgumentType.integer()).executes {
                this as Conf<Int>
                this.value = IntegerArgumentType.getInteger(it, "value")
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        is Short -> {
            id.then(argument<Int>("value", IntegerArgumentType.integer()).executes {
                this as Conf<Short>
                this.value = IntegerArgumentType.getInteger(it, "value").toShort()
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        is Byte -> {
            id.then(argument<Int>("value", IntegerArgumentType.integer()).executes {
                this as Conf<Byte>
                this.value = IntegerArgumentType.getInteger(it, "value").toByte()
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        is Float -> {
            id.then(argument<Float>("value", FloatArgumentType.floatArg()).executes {
                this as Conf<Float>
                this.value = FloatArgumentType.getFloat(it, "value")
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        is Double -> {
            id.then(argument<Double>("value", DoubleArgumentType.doubleArg()).executes {
                this as Conf<Double>
                this.value = DoubleArgumentType.getDouble(it, "value")
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        is Boolean -> {
            id.then(argument<Boolean>("value", BoolArgumentType.bool()).executes {
                this as Conf<Boolean>
                this.value = BoolArgumentType.getBool(it, "value")
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        is String -> {
            id.then(argument<String>("value", StringArgumentType.string()).executes {
                this as Conf<String>
                this.value = StringArgumentType.getString(it, "value")
                it.source.sendFeedback(i18n("command.set.success"))
                Command.SINGLE_SUCCESS
            })
        }

        else -> {
            return false
        }
    }
    return true
}