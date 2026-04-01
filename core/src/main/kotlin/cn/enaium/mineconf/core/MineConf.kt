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
import com.fasterxml.jackson.core.JsonProcessingException
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

    init {
        update(instance)
    }

    private fun update(instance: Any) {
        instance.javaClass.declaredFields.forEach { declaredField ->
            try {
                declaredField.setAccessible(true)
                val get = declaredField.get(instance)
                if (get is Conf<*>) {
                    confMap[get.id] = get
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
            if (confMap.containsKey(id)) {
                val conf = confMap[id] as Conf<Any>
                val value = conf.value
                try {
                    conf.value = ObjectMapper().readValue(o.get("value").toString(), value.javaClass)
                } catch (e: Throwable) {
                    RuntimeException("Unable to read config: " + conf.id, e).printStackTrace()
                }
            }
        }
        getConf(instance)
    }

    fun <T> getConf(o: T): T {
        o?.javaClass?.getDeclaredFields()?.forEach {
            it.setAccessible(true)
            try {
                val get = it.get(o)
                if (get is Conf<*>) {
                    val conf = get as Conf<Any?>
                    conf.value = confMap[conf.id]?.value
                }
            } catch (e: Throwable) {
                RuntimeException("Unable to get the conf: " + it.name, e).printStackTrace()
            }
        }

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
