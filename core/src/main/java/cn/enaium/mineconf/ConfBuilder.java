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

import cn.enaium.mineconf.conf.*;
import cn.enaium.mineconf.type.Vec2;
import cn.enaium.mineconf.type.Vec3;
import cn.enaium.mineconf.type.Vec4;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Enaium
 */
@SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
public class ConfBuilder {
    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private Widget widget;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder widget(Widget widget) {
            this.widget = widget;
            return this;
        }

        private void requires() {
            Objects.requireNonNull(id, "required id");
            Objects.requireNonNull(name, "required name");
            Objects.requireNonNull(description, "required description");
        }

        public LiteralBuilder literal() {
            requires();
            return new LiteralBuilder();
        }

        public class LiteralBuilder {
            public <T> Conf<T> build(T defaultValue) {
                final Conf<T> conf = new Conf<>(id, name, description, widget);
                conf.setValue(defaultValue);
                return conf;
            }
        }

        public static class RangeBuilder<T extends Number> {
            private T min;
            private T max;

            public RangeBuilder<T> min(T min) {
                this.min = min;
                return this;
            }

            public RangeBuilder<T> max(T max) {
                this.max = max;
                return this;
            }

            public Range<T> build() {
                return new Range<>(min, max);
            }
        }

        public <T extends Number> NumberBuilder<T> number() {
            requires();
            return new NumberBuilder<>();
        }

        public class NumberBuilder<T extends Number> {

            public Range<T> range;

            public NumberBuilder<T> range(Consumer<RangeBuilder<T>> range) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                range.accept(rangeBuilder);
                this.range = rangeBuilder.build();
                return this;
            }

            public <T extends Number> NumberConf<T> build(T defaultValue) {
                final NumberConf<T> numberConf = new NumberConf<>(id, name, description, widget, (Range<T>) range);
                numberConf.setValue(defaultValue);
                return numberConf;
            }
        }

        public <T extends Number> Vec2Builder<T> vec2() {
            requires();
            return new Vec2Builder<>();
        }

        public class Vec2Builder<T extends Number> {
            private Range<T> xRange;
            private Range<T> yRange;

            public Vec2Builder<T> xRange(Consumer<RangeBuilder<T>> xRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                xRange.accept(rangeBuilder);
                this.xRange = rangeBuilder.build();
                return this;
            }

            public Vec2Builder<T> yRange(Consumer<RangeBuilder<T>> yRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                yRange.accept(rangeBuilder);
                this.yRange = rangeBuilder.build();
                return this;
            }

            public <T extends Number> Vec2Conf<T> build(T defaultValueX, T defaultValueY) {
                final Vec2Conf<T> vec2Conf = new Vec2Conf<>(id, name, description, widget, (Range<T>) xRange, (Range<T>) yRange);
                vec2Conf.setValue(new Vec2<>(defaultValueX, defaultValueY));
                return vec2Conf;
            }
        }

        public <T extends Number> Vec3Builder<T> vec3() {
            requires();
            return new Vec3Builder<>();
        }

        public class Vec3Builder<T extends Number> {
            private Range<T> xRange;
            private Range<T> yRange;
            private Range<T> zRange;

            public Vec3Builder<T> xRange(Consumer<RangeBuilder<T>> xRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                xRange.accept(rangeBuilder);
                this.xRange = rangeBuilder.build();
                return this;
            }

            public Vec3Builder<T> yRange(Consumer<RangeBuilder<T>> yRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                yRange.accept(rangeBuilder);
                this.yRange = rangeBuilder.build();
                return this;
            }

            public Vec3Builder<T> zRange(Consumer<RangeBuilder<T>> zRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                zRange.accept(rangeBuilder);
                this.zRange = rangeBuilder.build();
                return this;
            }

            public <T extends Number> Vec3Conf<T> build(T defaultValueX, T defaultValueY, T defaultValueZ) {
                final Vec3Conf<T> vec3Conf = new Vec3Conf<>(id, name, description, widget, (Range<T>) xRange, (Range<T>) yRange, (Range<T>) zRange);
                vec3Conf.setValue(new Vec3<>(defaultValueX, defaultValueY, defaultValueZ));
                return vec3Conf;
            }
        }

        public <T extends Number> Vec4Builder<T> vec4() {
            requires();
            return new Vec4Builder<>();
        }

        public class Vec4Builder<T extends Number> {
            private Range<T> xRange;
            private Range<T> yRange;
            private Range<T> zRange;
            private Range<T> wRange;

            public Vec4Builder<T> xRange(Consumer<RangeBuilder<T>> xRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                xRange.accept(rangeBuilder);
                this.xRange = rangeBuilder.build();
                return this;
            }

            public Vec4Builder<T> yRange(Consumer<RangeBuilder<T>> yRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                yRange.accept(rangeBuilder);
                this.yRange = rangeBuilder.build();
                return this;
            }

            public Vec4Builder<T> zRange(Consumer<RangeBuilder<T>> zRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                zRange.accept(rangeBuilder);
                this.zRange = rangeBuilder.build();
                return this;
            }

            public Vec4Builder<T> wRange(Consumer<RangeBuilder<T>> wRange) {
                final RangeBuilder<T> rangeBuilder = new RangeBuilder<>();
                wRange.accept(rangeBuilder);
                this.wRange = rangeBuilder.build();
                return this;
            }

            public <T extends Number> Vec4Conf<T> build(T defaultValueX, T defaultValueY, T defaultValueZ, T defaultValueW) {
                final Vec4Conf<T> vec4Conf = new Vec4Conf<>(id, name, description, widget, (Range<T>) xRange, (Range<T>) yRange, (Range<T>) zRange, (Range<T>) wRange);
                vec4Conf.setValue(new Vec4<>(defaultValueX, defaultValueY, defaultValueZ, defaultValueW));
                return vec4Conf;
            }
        }

        public <T> OptionBuilder<T> option() {
            return new OptionBuilder<>();
        }

        public class OptionBuilder<T> {
            private List<T> options;

            public OptionBuilder<T> options(List<T> options) {
                this.options = options;
                return this;
            }

            public <T> OptionConf<T> build(T defaultValue) {
                final OptionConf<T> optionConf = new OptionConf<T>(id, name, description, widget, (Collection<T>) options);
                optionConf.setValue(defaultValue);
                return optionConf;
            }
        }

        public <T> CollectionConf<T> build(Collection<T> defaultValue) {
            requires();
            final CollectionConf<T> collectionConf = new CollectionConf<>(id, name, description, widget);
            collectionConf.setValue(defaultValue);
            return collectionConf;
        }

        public <O, M> OneToManyConf<O, M> build(Map<O, ? extends Collection<M>> defaultValue) {
            requires();
            final OneToManyConf<O, M> oneToManyConf = new OneToManyConf<>(id, name, description, widget);
            oneToManyConf.setValue(defaultValue);
            return oneToManyConf;
        }
    }
}
