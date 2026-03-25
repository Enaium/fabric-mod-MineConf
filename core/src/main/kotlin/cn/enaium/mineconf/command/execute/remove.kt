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

import cn.enaium.mineconf.MineConfLoader
import cn.enaium.mineconf.command.literal
import cn.enaium.mineconf.common.CommonSource
import cn.enaium.mineconf.conf.CollectionConf
import cn.enaium.mineconf.conf.MultimapConf
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
fun remove(): LiteralArgumentBuilder<CommonSource> {
    val append = literal("remove")
    MineConfLoader.MINE_CONF.forEach { (modId, mineConf) ->
        mineConf.getConf().forEach { (id, conf) ->
            val id = literal(id)
            when (conf) {
                is CollectionConf<*> -> {
                    conf.remove(id)
                }

                is MultimapConf<*, *> -> {
                    conf.remove(id)
                }

                else -> {
                    return@forEach
                }
            }
            val modId = literal(modId).then(id)
            append.then(modId)
        }
    }
    return append
}