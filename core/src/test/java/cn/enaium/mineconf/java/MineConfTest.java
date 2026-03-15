package cn.enaium.mineconf.java;

import cn.enaium.mineconf.ConfBuilder;
import cn.enaium.mineconf.MineConf;
import cn.enaium.mineconf.annotation.ConfField;
import cn.enaium.mineconf.conf.*;
import cn.enaium.mineconf.type.Vec2;
import cn.enaium.mineconf.type.Vec3;
import cn.enaium.mineconf.type.Vec4;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Enaium
 */
public class MineConfTest {
    @Test
    public void mineConf() throws JsonProcessingException {
        final MineConf conf = new MineConf("test", "Test");
        conf.register(new Config());
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
}
