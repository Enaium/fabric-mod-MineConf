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

/**
 * @author Enaium
 */
public class NumberConf<T extends Number> extends Conf<T> {

    private final Range<T> range;

    public NumberConf(String id, String name, String description, Widget widget, Range<T> range) {
        super(id, name, description, widget);
        this.range = range;
    }

    public Range<T> getRange() {
        return range;
    }
}
