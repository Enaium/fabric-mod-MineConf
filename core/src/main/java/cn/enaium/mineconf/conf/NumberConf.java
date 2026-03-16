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

import org.jspecify.annotations.Nullable;

/**
 * @author Enaium
 */
public class NumberConf<T extends Number> extends Conf<T> {

    @Nullable
    private final Range<T> range;
    private final T step;

    public NumberConf(String id, String name, String description, Widget widget, @Nullable Range<T> range, T step) {
        super(id, name, description, widget);
        this.range = range;
        this.step = step;
    }

    public @Nullable Range<T> getRange() {
        return range;
    }

    public T getStep() {
        return step;
    }
}
