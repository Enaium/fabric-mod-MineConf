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
package cn.enaium.mineconf.core

import cn.enaium.mineconf.core.conf.Conf
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * @author Enaium
 */
object MineConfLoader {
    val MINE_CONF: MutableMap<String, MineConf> = HashMap<String, MineConf>()

    fun path(name: String): Path {
        return Path(System.getProperty("user.dir")).resolve("MineConf").resolve("$name.json")
    }

    @JvmStatic
    fun load() {
        for (mineConfService in ServiceLoader.load(MineConfService::class.java)) {
            val conf = mineConfService.conf()
            val path = path(conf.name)
            if (Files.exists(path)) {
                try {
                    conf.read(path.readText())
                } catch (e: IOException) {
                    throw RuntimeException("Unable to load config file: $path", e)
                }
            } else {
                save(conf)
            }
            MINE_CONF[conf.id] = conf
        }
        Runtime.getRuntime().addShutdownHook(Thread(::save))
    }

    fun save(conf: MineConf) {
        val path = path(conf.name)
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.parent)
            }
            Files.write(path, conf.write().toByteArray(StandardCharsets.UTF_8))
        } catch (e: IOException) {
            throw RuntimeException("Unable to save config file: $path", e)
        }
    }

    @JvmStatic
    fun save() {
        MINE_CONF.forEach { (_, conf) -> save(conf) }
    }

    @JvmStatic
    fun getMineConf(any: Conf<*>): MineConf? {
        MINE_CONF.forEach { (_, mineConf) ->
            mineConf.getConf().forEach { (_, conf) ->
                if (any == conf) {
                    return mineConf
                }
            }
        }
        return null
    }
}
