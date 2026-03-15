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
    private final Map<String, Conf<?>> confMap = new HashMap<>();

    public MineConf(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MineConf register(Conf<?> conf) {
        confMap.put(conf.getId(), conf);
        return this;
    }

    public MineConf register(Object o) {
        for (Field declaredField : o.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                final ConfField annotation = declaredField.getAnnotation(ConfField.class);
                if (annotation == null) {
                    continue;
                }
                final Conf<?> conf = (Conf<?>) declaredField.get(o);
                confMap.put(conf.getId(), conf);
            } catch (Throwable e) {
                throw new RuntimeException("Unable to register the conf: " + declaredField.getName(), e);
            }
        }
        return this;
    }

    public String write() throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(confMap);
    }

    @SuppressWarnings("unchecked")
    public void read(String text) throws JsonProcessingException {
        final Map<String, ObjectNode> stringValueMap = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(text, new TypeReference<Map<String, ObjectNode>>() {
                });

        for (Map.Entry<String, ObjectNode> stringObjectNodeEntry : stringValueMap.entrySet()) {
            if (confMap.containsKey(stringObjectNodeEntry.getKey())) {
                final Conf<Object> conf = (Conf<Object>) confMap.get(stringObjectNodeEntry.getKey());
                final Object value = conf.getValue();
                if (value != null) {
                    conf.setValue(new ObjectMapper().readValue(stringObjectNodeEntry.getValue().get("value").toString(), value.getClass()));
                }
            }
        }
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
}
