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

import cn.enaium.mineconf.conf.OptionConf
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun OptionConf<*>.option(id: LiteralArgumentBuilder<Any>) {
    this as OptionConf<Any>
    this.options.forEach {
        id.then(literal<Any>(it.toString()).executes { _ ->
            this.value = it
            Command.SINGLE_SUCCESS
        })
    }
}