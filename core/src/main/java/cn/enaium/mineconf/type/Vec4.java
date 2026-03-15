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

package cn.enaium.mineconf.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Vec4<T extends Number> {
    private final T x;
    private final T y;
    private final T z;
    private final T w;

    public Vec4(@JsonProperty("x") T x, @JsonProperty("y") T y, @JsonProperty("z") T z, @JsonProperty("w") T w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public T getZ() {
        return z;
    }

    public T getW() {
        return w;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vec4<?> vec4 = (Vec4<?>) o;
        return Objects.equals(x, vec4.x) && Objects.equals(y, vec4.y) && Objects.equals(z, vec4.z) && Objects.equals(w, vec4.w);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }
}