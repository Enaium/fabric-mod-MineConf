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

package cn.enaium.mineconf.conf;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Enaium
 */
public class Conf<T> {

    /**
     * Unique id of the conf.
     */
    @JsonIgnore
    private final String id;

    /**
     * Name of the conf.
     */
    private final String name;

    /**
     * Description of the conf.
     */
    private final String description;

    /**
     * Widget of the conf.
     */
    private final Widget widget;

    /**
     * Value of the conf.
     */
    private T value;

    public Conf(String id, String name, String description, Widget widget) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.widget = widget;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Widget getWidget() {
        return widget;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
