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

import cn.enaium.mineconf.type.Vec2;

/**
 * @author Enaium
 */
public class Vec2Conf<T extends Number> extends Conf<Vec2<T>> {

    private final Range<T> xRange;
    private final Range<T> yRange;

    public Vec2Conf(String id, String name, String description, Widget widget, Range<T> xRange, Range<T> yRange) {
        super(id, name, description, widget);
        this.xRange = xRange;
        this.yRange = yRange;
    }

    public Range<T> getXRange() {
        return xRange;
    }

    public Range<T> getYRange() {
        return yRange;
    }
}
