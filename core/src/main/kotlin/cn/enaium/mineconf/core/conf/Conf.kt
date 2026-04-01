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

import cn.enaium.mineconf.core.MineConfLoader
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * @author Enaium
 */
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
    private var _value = defaultValue
    var value
        get() = _value ?: defaultValue
        set(value) {
            _value = value
            MineConfLoader.getMineConf(this)?.also {
                MineConfLoader.save()
            }
        }

    fun valueString(): String {
        return ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this.value)
    }
}
