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

package cn.enaium.mineconf;

import cn.enaium.mineconf.annotation.ConfField;
import cn.enaium.mineconf.conf.Conf;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Enaium
 */
public class MineConf {

    /**
     * Unique id of your mod.
     */
    private final String id;

    /**
     * Unique name of the conf.
     */
    private final String name;

    /**
     * Config object instance
     */
    private final Object instance;
    private final Map<String, Conf<?>> confMap = new HashMap<>();

    public MineConf(String id, String name, Object instance) {
        this.id = id;
        this.name = name;
        this.instance = instance;

        register(instance);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void register(Object instance) {
        for (Field declaredField : instance.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                final ConfField annotation = declaredField.getAnnotation(ConfField.class);
                if (annotation == null) {
                    continue;
                }
                final Conf<?> conf = (Conf<?>) declaredField.get(instance);
                confMap.put(conf.getId(), conf);
            } catch (Throwable e) {
                throw new RuntimeException("Unable to register the conf: " + declaredField.getName(), e);
            }
        }

        if (confMap.isEmpty()) {
            throw new IllegalStateException("The config is empty.");
        }
    }

    public String write() throws JsonProcessingException {
        return new ObjectMapper().setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(confMap);
    }

    @SuppressWarnings("unchecked")
    public void read(String text) throws JsonProcessingException {
        final Map<String, ObjectNode> stringValueMap = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(text, new TypeReference<Map<String, ObjectNode>>() {
                });

        stringValueMap.forEach((id, o) -> {
            if (confMap.containsKey(id)) {
                final Conf<Object> conf = (Conf<Object>) confMap.get(id);
                final Object value = conf.getValue();
                if (value != null) {
                    try {
                        conf.setValue(new ObjectMapper().readValue(o.get("value").toString(), value.getClass()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Unable to read config: " + conf.getId(), e);
                    }
                }
            }
        });
        getConf(instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T getConf(T o) {
        for (Field declaredField : o.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                final Conf<Object> conf = (Conf<Object>) declaredField.get(o);
                conf.setValue(confMap.get(conf.getId()).getValue());
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to get the conf: " + declaredField.getName(), e);
            }
        }
        return o;
    }

    public Conf<?> getConf(String id) {
        return confMap.get(id);
    }

    public Map<String, Conf<?>> getConfMap() {
        return confMap;
    }
}
