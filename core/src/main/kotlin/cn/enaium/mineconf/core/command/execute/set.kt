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
import cn.enaium.mineconf.core.common.CommonSource
import cn.enaium.mineconf.core.conf.*
import com.mojang.brigadier.builder.LiteralArgumentBuilder

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
fun set(): LiteralArgumentBuilder<CommonSource> {
    val set = cn.enaium.mineconf.core.command.literal("set")
    MineConfLoader.MINE_CONF.forEach { (modId, mineConf) ->
        mineConf.getConf().forEach { (id, conf) ->
            val id = cn.enaium.mineconf.core.command.literal(id)

            when (conf) {
                is NumberConf<*> -> {
                    conf.number(id)
                }

                is OptionConf<*> -> {
                    conf.option(id)
                }

                is EnumConf<*> -> {
                    conf.enum(id)
                }

                is Vec2Conf<*> -> {
                    conf.vec2(id)
                }

                is Vec3Conf<*> -> {
                    conf.vec3(id)
                }

                is Vec4Conf<*> -> {
                    conf.vec4(id)
                }

                else -> {
                    if (!conf.literal(id)) {
                        return@forEach
                    }
                }
            }
            val modId = cn.enaium.mineconf.core.command.literal(modId).then(id)
            set.then(modId)
        }
    }
    return set
}