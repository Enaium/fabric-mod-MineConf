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
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode

/**
 * @author Enaium
 */
@Suppress("UNCHECKED_CAST")
class MineConf(
    /**
     * Unique id of your mod.
     */
    val id: String,
    /**
     * Unique name of the conf.
     */
    val name: String,
    /**
     * Config object instance
     */
    private val instance: Any
) {
    private val confMap: MutableMap<String, Conf<*>> = HashMap()

    /**
     * Current values of the confs, keyed by the conf id. Kept here, so a conf keeps its value when the
     * instance in the config object is replaced by a copy.
     */
    private val confValues: MutableMap<String, Any?> = HashMap()

    init {
        update(instance)
    }

    internal fun confValue(id: String): Any? {
        return confValues[id]
    }

    internal fun putConfValue(id: String, value: Any?) {
        confValues[id] = value
    }

    internal fun putConfValueIfAbsent(id: String, value: Any?) {
        if (!confValues.containsKey(id)) {
            confValues[id] = value
        }
    }

    private fun update(instance: Any) {
        instance.javaClass.declaredFields.forEach { declaredField ->
            try {
                declaredField.setAccessible(true)
                val get = declaredField.get(instance)
                if (get is Conf<*>) {
                    confMap[get.id] = get
                    get.bind(this)
                }
            } catch (e: Throwable) {
                throw RuntimeException("Unable to register the conf: " + declaredField.name, e)
            }
        }

        check(!confMap.isEmpty()) { "The config is empty." }
    }

    fun write(): String {
        update(instance)
        return ObjectMapper().setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter()
            .writeValueAsString(confMap)
    }

    fun read(text: String) {
        val stringValueMap = ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .readValue(text, object : TypeReference<MutableMap<String, ObjectNode>>() {})

        stringValueMap.forEach { (id: String, o: ObjectNode) ->
            val conf = confMap[id] as? Conf<Any> ?: return@forEach
            val node = o.get("value") ?: return@forEach
            if (node.isNull) {
                return@forEach
            }
            try {
                val value = ObjectMapper().readValue(node.toString(), conf.value.javaClass)
                // An empty collection or map is not a value, so the default of the conf is kept.
                if (value is Collection<*> && value.isEmpty()) {
                    return@forEach
                }
                if (value is Map<*, *> && value.isEmpty()) {
                    return@forEach
                }
                putConfValue(id, value)
            } catch (e: Throwable) {
                RuntimeException("Unable to read config: " + conf.id, e).printStackTrace()
            }
        }
        update(instance)
    }

    fun <T> getConf(o: T): T {
        update(instance)
        return o
    }

    fun getConf(id: String): Conf<*>? {
        update(instance)
        return confMap[id]
    }

    fun getConf(): Map<String, Conf<*>> {
        update(instance)
        return confMap
    }
}
