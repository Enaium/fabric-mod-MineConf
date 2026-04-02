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

import cn.enaium.mineconf.core.command.literal
import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.core.common.text.Color
import cn.enaium.mineconf.core.common.text.Text
import cn.enaium.mineconf.core.conf.OptionConf
import cn.enaium.mineconf.core.utility.i18n
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun OptionConf<*>.option(id: LiteralArgumentBuilder<CommonSource>) {
    this as OptionConf<Any>
    this.options.forEach {
        id.then(literal(it.toString()).executes { context ->
            this.value = it
            context.source.sendFeedback(Text(i18n("command.set.success")).style {
                color = Color.GREEN
            })
            Command.SINGLE_SUCCESS
        })
    }
}