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
package cn.enaium.mineconf.core.conf

import cn.enaium.mineconf.core.MineConf
import cn.enaium.mineconf.core.MineConfLoader
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
open class Conf<T>(
    /**
     * Unique id of the conf.
     */
    @get:JsonIgnore
    open val id: String,
    /**
     * Name of the conf.
     */
    open val name: String,
    /**
     * Description of the conf.
     */
    open val description: String,
    /**
     * Value of the conf.
     */
    @get:JsonIgnore
    open val defaultValue: T,
    /**
     * Widget of the conf.
     */
    @get:JsonIgnore
    open var widget: Widget?
) {
    /**
     * The [MineConf] this conf is registered to. The current value is kept there, so the value is not lost
     * when the conf instance is replaced by a copy, e.g. to change the options or the default value.
     */
    @get:JsonIgnore
    @set:JsonIgnore
    internal var owner: MineConf? = null

    /**
     * Value of a conf that is not registered yet.
     */
    private var local: T? = null

    var value: T
        get() = owner?.confValue(id) as T? ?: local ?: defaultValue
        set(value) {
            val owner = owner ?: MineConfLoader.getMineConf(this).also { this.owner = it }
            if (owner == null) {
                local = value
            } else {
                owner.putConfValue(id, value)
                MineConfLoader.save()
            }
        }

    /**
     * Binds this conf to [mineConf] and moves a value that was set before the registration over.
     */
    internal fun bind(mineConf: MineConf) {
        if (owner === mineConf) {
            return
        }
        owner = mineConf
        local?.also {
            mineConf.putConfValueIfAbsent(id, it)
            local = null
        }
    }

    fun valueString(): String {
        return ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this.value)
    }
}
