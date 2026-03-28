package cn.enaium.mineconf.java;

import cn.enaium.mineconf.core.ConfBuilder;
import cn.enaium.mineconf.core.MineConf;
import cn.enaium.mineconf.conf.*;
import cn.enaium.mineconf.core.conf.*;
import cn.enaium.mineconf.core.type.Vec2;
import cn.enaium.mineconf.core.type.Vec3;
import cn.enaium.mineconf.core.type.Vec4;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Enaium
 */
public class MineConfTest {
    @Test
    public void mineConf() throws JsonProcessingException {
        final MineConf conf = new MineConf("test", "Test", new Config());
        final String write = conf.write();
        conf.read(write);
        final Config out = conf.getConf(new Config());
        assertEquals("Hello World!", out.literalString.getValue());
        assertEquals(Long.MAX_VALUE, out.literalLong.getValue());
        assertEquals(Integer.MAX_VALUE, out.literalInteger.getValue());
        assertEquals(Short.MAX_VALUE, out.literalShort.getValue());
        assertEquals(Byte.MAX_VALUE, out.literalByte.getValue());
        assertEquals(true, out.literalBoolean.getValue());
        assertEquals(Double.MAX_VALUE, out.literalDouble.getValue());
        assertEquals(Float.MAX_VALUE, out.literalFloat.getValue());
        assertEquals("Option 3", out.optionString.getValue());
        assertEquals(new Vec2<>(0, 0), out.vec2Conf.getValue());
        assertEquals(new Vec3<>(0, 0, 0), out.vec3Conf.getValue());
        assertEquals(new Vec4<>(0, 0, 0, 0), out.vec4Conf.getValue());
        assertArrayEquals(new String[]{"1", "2", "3"}, out.collectionConf.getValue().toArray(new String[0]));
    }

    public static class Config {
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

        OptionConf<String> optionString
                = ConfBuilder.create()
                .id("option.type.string")
                .name("String Conf")
                .description("This is a string conf")
                .option()
                .options(Arrays.asList("Option 1", "Option 2", "Option 3"))
                .build("Option 3");

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
                .<String>collection().build(Arrays.asList("1", "2", "3"));

        public Map<String, Collection<String>> multimap() {
            Map<String, Collection<String>> map = new HashMap<>();
            map.put("1", Arrays.asList("1-1", "1-2", "1-3"));
            map.put("2", Arrays.asList("2-1", "2-2", "2-3"));
            return map;
        }

        MultimapConf<String, String> multimapConf = ConfBuilder.create()
                .id("one-to-many.type.string")
                .name("One-to-Many Conf")
                .description("This is a one-to-many conf")
                .<String, String>multimap().build(multimap());
    }
}
