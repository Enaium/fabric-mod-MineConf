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

package cn.enaium.mineconf.core.utility

import cn.enaium.mineconf.core.config.MineConfConfig
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*

fun i18n(key: String): String {
    val lang: String = MineConfConfig.lang.value.name.lowercase(Locale.getDefault())
    try {
        var url = object {}::class.java.getResource("/lang/$lang.json")
        if (url == null) {
            url = object {}::class.java.getResource("/lang/en_us.json")
        }

        if (url == null) {
            return key
        }

        val text = url.readText()
        return ObjectMapper().readValue<ObjectNode>(text).get(key).asText()
    } catch (_: Throwable) {
    }
    return key
}