package cn.enaium.mineconf.java;

import cn.enaium.mineconf.core.ConfBuilder;
import cn.enaium.mineconf.conf.*;
import cn.enaium.mineconf.core.conf.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Enaium
 */
public class BuilderTest {
    @Test
    public void literal() {
        final Conf<String> build = ConfBuilder.<String>create()
                .id("literal.type.string")
                .name("Literal string Conf")
                .description("This is a literal string conf")
                .<String>literal()
                .build("Hello World!");

        assertEquals("hello world!", build.getValue().toLowerCase());
    }

    @Test
    public void number() {
        final NumberConf<Integer> build = ConfBuilder.create()
                .id("number.type.int")
                .name("Number Conf")
                .description("This is a number conf")
                .<Integer>number()
                .range((range) -> {
                    range.min(1);
                    range.max(2);
                }).build(1);
        assertEquals(1, build.getValue());
        assertEquals(1, Objects.requireNonNull(build.getRange()).getMin());
        assertEquals(2, build.getRange().getMax());
    }

    @Test
    public void vec2() {
        final Vec2Conf<Integer> build = ConfBuilder.create()
                .id("vec2.type.int")
                .name("Vec2 Conf")
                .description("This is a vec2 conf")
                .<Integer>vec2()
                .rangeX(x -> x.min(1).max(10))
                .rangeY(y -> y.min(1).max(10))
                .build(1, 1);
        assertEquals(1, Objects.requireNonNull(build.getRangeX()).getMin());
        assertEquals(10, build.getRangeX().getMax());

        assertEquals(1, Objects.requireNonNull(build.getRangeY()).getMin());
        assertEquals(10, build.getRangeY().getMax());

        assertEquals(1, build.getValue().getX());
        assertEquals(1, build.getValue().getY());
    }

    @Test
    public void vec3() {
        final Vec3Conf<Integer> build = ConfBuilder.create()
                .id("vec3.type.int")
                .name("Vec3 Conf")
                .description("This is a vec2 conf")
                .<Integer>vec3()
                .rangeX(x -> x.min(1).max(10))
                .rangeY(y -> y.min(1).max(10))
                .rangeZ(z -> z.min(1).max(10))
                .build(1, 1, 1);
        assertEquals(1, Objects.requireNonNull(build.getRangeX()).getMin());
        assertEquals(10, build.getRangeX().getMax());

        assertEquals(1, Objects.requireNonNull(build.getRangeY()).getMin());
        assertEquals(10, build.getRangeY().getMax());

        assertEquals(1, Objects.requireNonNull(build.getRangeZ()).getMin());
        assertEquals(10, build.getRangeZ().getMax());

        assertEquals(1, build.getValue().getX());
        assertEquals(1, build.getValue().getY());
        assertEquals(1, build.getValue().getZ());
    }

    @Test
    public void vec4() {
        final Vec4Conf<Integer> build = ConfBuilder.create()
                .id("vec4.type.int")
                .name("Vec4 Conf")
                .description("This is a vec4 conf")
                .<Integer>vec4()
                .rangeX(x -> x.min(1).max(10))
                .rangeY(y -> y.min(1).max(10))
                .rangeZ(z -> z.min(1).max(10))
                .rangeW(w -> w.min(1).max(10))
                .build(1, 1, 1, 1);
        assertEquals(1, Objects.requireNonNull(build.getRangeX()).getMin());
        assertEquals(10, build.getRangeX().getMax());

        assertEquals(1, Objects.requireNonNull(build.getRangeY()).getMin());
        assertEquals(10, build.getRangeY().getMax());

        assertEquals(1, Objects.requireNonNull(build.getRangeZ()).getMin());
        assertEquals(10, build.getRangeZ().getMax());

        assertEquals(1, Objects.requireNonNull(build.getRangeW()).getMin());
        assertEquals(10, build.getRangeW().getMax());

        assertEquals(1, build.getValue().getX());
        assertEquals(1, build.getValue().getY());
        assertEquals(1, build.getValue().getZ());
        assertEquals(1, build.getValue().getW());
    }

    @Test
    public void option() {
        final OptionConf<Integer> build = ConfBuilder.create()
                .id("option.type.int")
                .name("Option Conf")
                .description("This is a option conf")
                .option().options(Arrays.asList(1, 2, 3)).build(1);
        assertEquals(1, build.getValue());
    }

    enum MethodType {
        METHOD1,
        METHOD2,
        METHOD3,
    }

    @Test
    public void e() {
        final Conf<MethodType> build = ConfBuilder.create()
                .id("enum.type.enum")
                .name("Enum Conf")
                .description("This is a enum conf")
                .<MethodType>literal().build(MethodType.METHOD1);
        assertEquals(MethodType.METHOD1, build.getValue());
    }

    @Test
    public void requires() {
        assertThrows(NullPointerException.class, () -> ConfBuilder.create().literal().build(""));
    }
}
