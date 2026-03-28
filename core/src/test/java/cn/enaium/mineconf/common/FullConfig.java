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

import cn.enaium.mineconf.core.ConfBuilder;
import cn.enaium.mineconf.conf.*;
import cn.enaium.mineconf.core.conf.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FullConfig {
    Conf<String> literalString
            = ConfBuilder.create()
            .id("literal.type.string")
            .name("String Conf")
            .description("This is a string conf")
            .<String>literal()
            .build("Hello World!");

    Conf<Long> literalLong
            = ConfBuilder.create()
            .id("literal.type.long")
            .name("Long Conf")
            .description("This is a string conf")
            .<Long>literal()
            .build(Long.MAX_VALUE);

    Conf<Integer> literalInteger
            = ConfBuilder.create()
            .id("literal.type.integer")
            .name("Integer Conf")
            .description("This is an integer conf")
            .<Integer>literal()
            .build(Integer.MAX_VALUE);

    Conf<Short> literalShort
            = ConfBuilder.create()
            .id("literal.type.short")
            .name("Short Conf")
            .description("This is a short conf")
            .<Short>literal()
            .build(Short.MAX_VALUE);

    Conf<Byte> literalByte
            = ConfBuilder.create()
            .id("literal.type.byte")
            .name("Byte Conf")
            .description("This is a byte conf")
            .<Byte>literal()
            .build(Byte.MAX_VALUE);

    Conf<Boolean> literalBoolean
            = ConfBuilder.create()
            .id("literal.type.boolean")
            .name("Boolean Conf")
            .description("This is a boolean conf")
            .<Boolean>literal()
            .build(true);

    Conf<Double> literalDouble
            = ConfBuilder.create()
            .id("literal.type.double")
            .name("Double Conf")
            .description("This is a double conf")
            .<Double>literal()
            .build(Double.MAX_VALUE);

    Conf<Float> literalFloat
            = ConfBuilder.create()
            .id("literal.type.float")
            .name("Float Conf")
            .description("This is a float conf")
            .<Float>literal()
            .build(Float.MAX_VALUE);

    NumberConf<Long> numberLong
            = ConfBuilder.create()
            .id("number.type.long")
            .name("Long Number Conf")
            .description("This is a long number Conf")
            .<Long>number()
            .range(r -> r.min(-10L).max(10L)).step(1L).build(0L);

    NumberConf<Integer> numberInteger
            = ConfBuilder.create()
            .id("number.type.integer")
            .name("Integer Number Conf")
            .description("This is an integer number Conf")
            .<Integer>number()
            .range(r -> r.min(-10).max(10)).step(1).build(0);

    NumberConf<Short> numberShort
            = ConfBuilder.create()
            .id("number.type.short")
            .name("Short Number Conf")
            .description("This is a short number Conf")
            .<Short>number()
            .range(r -> r.min((short) -10).max((short) 10)).step((short) 1).build((short) 0);

    NumberConf<Byte> numberByte
            = ConfBuilder.create()
            .id("number.type.byte")
            .name("Byte Number Conf")
            .description("This is a byte number Conf")
            .<Byte>number()
            .range(r -> r.min((byte) -10).max((byte) 10)).step((byte) 1).build((byte) 0);

    NumberConf<Float> numberFloat
            = ConfBuilder.create()
            .id("number.type.float")
            .name("Float Number Conf")
            .description("This is a float number Conf")
            .<Float>number()
            .range(r -> r.min(-10f).max(10f)).step(0.5f).build(0f);

    NumberConf<Double> numberDouble
            = ConfBuilder.create()
            .id("number.type.double")
            .name("Double Number Conf")
            .description("This is a double number Conf")
            .<Double>number()
            .range(r -> r.min(-10.0).max(10.0)).step(0.5).build(0.0);

    OptionConf<String> optionString
            = ConfBuilder.create()
            .id("option.type.string")
            .name("String Conf")
            .description("This is a string option conf")
            .option()
            .options(Arrays.asList("Option 1", "Option 2", "Option 3"))
            .build("Option 3");

    enum Method {
        METHOD1,
        METHOD2,
        METHOD3,
    }

    EnumConf<Method> enumConf = ConfBuilder.create()
            .id("enum.type.method")
            .name("Enum Conf")
            .description("This is a enum conf")
            .<Method>enumeration()
            .type(Method.class)
            .build(Method.METHOD1);

    Vec2Conf<Integer> vec2Conf
            = ConfBuilder.create()
            .id("vec2.type.integer")
            .name("Vec2 Conf")
            .description("This is a vec2 conf")
            .<Integer>vec2().build(0, 0);

    Vec3Conf<Integer> vec3Conf
            = ConfBuilder.create()
            .id("vec3.type.integer")
            .name("Vec3 Conf")
            .description("This is a vec3 conf")
            .<Integer>vec3().build(0, 0, 0);


    Vec4Conf<Integer> vec4Conf
            = ConfBuilder.create()
            .id("vec4.type.integer")
            .name("Vec4 Conf")
            .description("This is a vec4 conf")
            .<Integer>vec4().build(0, 0, 0, 0);

    CollectionConf<String> collectionConf = ConfBuilder.create()
            .id("collection.type.string")
            .name("Collection Conf")
            .description("This is a collection conf")
            .<String>collection()
            .options(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")).build(Arrays.asList("1", "2", "3"));

    public Map<String, Collection<String>> multimap() {
        Map<String, Collection<String>> map = new HashMap<>();
        map.put("1", Arrays.asList("1-1", "1-2", "1-3"));
        map.put("2", Arrays.asList("2-1", "2-2", "2-3"));
        return map;
    }

    MultimapConf<String, String> multimapConf = ConfBuilder.create()
            .id("multimap.type.string")
            .name("Multimap Conf")
            .description("This is a Multimap conf")
            .<String, String>multimap()
            .build(multimap());
}