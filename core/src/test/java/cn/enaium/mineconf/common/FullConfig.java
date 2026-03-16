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

package cn.enaium.mineconf.common;

import cn.enaium.mineconf.ConfBuilder;
import cn.enaium.mineconf.annotation.ConfField;
import cn.enaium.mineconf.conf.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FullConfig {
    @ConfField
    Conf<String> literalString
            = ConfBuilder.create()
            .id("literal.type.string")
            .name("String Conf")
            .description("This is a string conf")
            .literal()
            .build("Hello World!");

    @ConfField
    Conf<Long> literalLong
            = ConfBuilder.create()
            .id("literal.type.long")
            .name("Long Conf")
            .description("This is a string conf")
            .literal()
            .build(Long.MAX_VALUE);

    @ConfField
    Conf<Integer> literalInteger
            = ConfBuilder.create()
            .id("literal.type.integer")
            .name("Integer Conf")
            .description("This is an integer conf")
            .literal()
            .build(Integer.MAX_VALUE);

    @ConfField
    Conf<Short> literalShort
            = ConfBuilder.create()
            .id("literal.type.short")
            .name("Short Conf")
            .description("This is a short conf")
            .literal()
            .build(Short.MAX_VALUE);

    @ConfField
    Conf<Byte> literalByte
            = ConfBuilder.create()
            .id("literal.type.byte")
            .name("Byte Conf")
            .description("This is a byte conf")
            .literal()
            .build(Byte.MAX_VALUE);

    @ConfField
    Conf<Boolean> literalBoolean
            = ConfBuilder.create()
            .id("literal.type.boolean")
            .name("Boolean Conf")
            .description("This is a boolean conf")
            .literal()
            .build(true);

    @ConfField
    Conf<Double> literalDouble
            = ConfBuilder.create()
            .id("literal.type.double")
            .name("Double Conf")
            .description("This is a double conf")
            .literal()
            .build(Double.MAX_VALUE);

    @ConfField
    Conf<Float> literalFloat
            = ConfBuilder.create()
            .id("literal.type.float")
            .name("Float Conf")
            .description("This is a float conf")
            .literal()
            .build(Float.MAX_VALUE);

    @ConfField
    NumberConf<Long> numberLong
            = ConfBuilder.create()
            .id("number.type.long")
            .name("Long Number Conf")
            .description("This is a long number Conf")
            .number()
            .range(r -> r.min(-10L).max(10L)).step(1L).build(0L);

    @ConfField
    NumberConf<Integer> numberInteger
            = ConfBuilder.create()
            .id("number.type.integer")
            .name("Integer Number Conf")
            .description("This is an integer number Conf")
            .number()
            .range(r -> r.min(-10).max(10)).step(1).build(0);

    @ConfField
    NumberConf<Short> numberShort
            = ConfBuilder.create()
            .id("number.type.short")
            .name("Short Number Conf")
            .description("This is a short number Conf")
            .number()
            .range(r -> r.min(-10).max(10)).step(1).build((short) 0);

    @ConfField
    NumberConf<Byte> numberByte
            = ConfBuilder.create()
            .id("number.type.byte")
            .name("Byte Number Conf")
            .description("This is a byte number Conf")
            .number()
            .range(r -> r.min(-10).max(10)).step(1).build((byte) 0);

    @ConfField
    NumberConf<Float> numberFloat
            = ConfBuilder.create()
            .id("number.type.float")
            .name("Float Number Conf")
            .description("This is a float number Conf")
            .number()
            .range(r -> r.min(-10f).max(10f)).step(0.5f).build(0f);

    @ConfField
    NumberConf<Double> numberDouble
            = ConfBuilder.create()
            .id("number.type.double")
            .name("Double Number Conf")
            .description("This is a double number Conf")
            .number()
            .range(r -> r.min(-10.0).max(10.0)).step(0.5).build(0.0);

    @ConfField
    OptionConf<String> optionString
            = ConfBuilder.create()
            .id("option.type.string")
            .name("String Conf")
            .description("This is a string conf")
            .option()
            .options(Arrays.asList("Option 1", "Option 2", "Option 3"))
            .build("Option 3");

    @ConfField
    Vec2Conf<Integer> vec2Conf
            = ConfBuilder.create()
            .id("vec2.type.integer")
            .name("Vec2 Conf")
            .description("This is a vec2 conf")
            .vec2().build(0, 0);

    @ConfField
    Vec3Conf<Integer> vec3Conf
            = ConfBuilder.create()
            .id("vec3.type.integer")
            .name("Vec3 Conf")
            .description("This is a vec3 conf")
            .vec3().build(0, 0, 0);


    @ConfField
    Vec4Conf<Integer> vec4Conf
            = ConfBuilder.create()
            .id("vec4.type.integer")
            .name("Vec4 Conf")
            .description("This is a vec4 conf")
            .vec4().build(0, 0, 0, 0);

    @ConfField
    CollectionConf<String> collectionConf = ConfBuilder.create()
            .id("collection.type.string")
            .name("Collection Conf")
            .description("This is a collection conf")
            .build(Arrays.asList("1", "2", "3"));

    public Map<String, Collection<String>> oneToMany() {
        Map<String, Collection<String>> map = new HashMap<>();
        map.put("1", Arrays.asList("1-1", "1-2", "1-3"));
        map.put("2", Arrays.asList("2-1", "2-2", "2-3"));
        return map;
    }

    @ConfField
    OneToManyConf<String, String> oneToManyConf = ConfBuilder.create()
            .id("one-to-many.type.string")
            .name("One-to-Many Conf")
            .description("This is a one-to-many conf")
            .build(oneToMany());
}