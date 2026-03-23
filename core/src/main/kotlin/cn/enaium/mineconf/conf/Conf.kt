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
package cn.enaium.mineconf.conf

import cn.enaium.mineconf.MineConfLoader
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * @author Enaium
 */
open class Conf<T>(
    /**
     * Unique id of the conf.
     */
    @field:JsonIgnore
    val id: String,
    /**
     * Name of the conf.
     */
    val name: String,
    /**
     * Description of the conf.
     */
    val description: String,
    /**
     * Value of the conf.
     */
    var defaultValue: T,
    /**
     * Widget of the conf.
     */
    @field:JsonIgnore
    var widget: Widget?
) {
    var value
        get() = this.defaultValue
        set(value) {
            this.defaultValue = value
            MineConfLoader.getMineConf(this)?.also {
                MineConfLoader.save()
            }
        }

    fun valueString(): String {
        return ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this.value)
    }
}
