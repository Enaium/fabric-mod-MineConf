package cn.enaium.mineconf.kt

import cn.enaium.mineconf.ConfBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

/**
 * @author Enaium
 */
class BuilderTest {
    @Test
    fun literal() {
        val build = ConfBuilder.create()
            .id("literal.type.string")
            .name("Literal string Conf")
            .description("This is a literal string conf")
            .literal<String>()
            .build("Hello World!")

        Assertions.assertEquals("hello world!", build.value.lowercase(Locale.getDefault()))
    }

    @Test
    fun number() {
        val build = ConfBuilder.create()
            .id("number.type.int")
            .name("Number Conf")
            .description("This is a number conf")
            .number<Int>().range {
                it.min(1).max(2)
            }.build(1)
        Assertions.assertEquals(1, build.value)
        Assertions.assertEquals(1, build.range?.min)
        Assertions.assertEquals(2, build.range?.max)
    }

    @Test
    fun vec2() {
        val build = ConfBuilder.create()
            .id("vec2.type.int")
            .name("Vec2 Conf")
            .description("This is a vec2 conf")
            .vec2<Number>()
            .xRange { it.min(1).max(10) }
            .yRange { it.min(1).max(10) }
            .build(1, 1)
        Assertions.assertEquals(1, build.xRange?.min)
        Assertions.assertEquals(10, build.xRange?.max)

        Assertions.assertEquals(1, build.yRange?.min)
        Assertions.assertEquals(10, build.yRange?.max)

        Assertions.assertEquals(1, build.value.x)
        Assertions.assertEquals(1, build.value.y)
    }

    @Test
    fun vec3() {
        val build = ConfBuilder.create()
            .id("vec3.type.int")
            .name("Vec3 Conf")
            .description("This is a vec2 conf")
            .vec3<Int>()
            .xRange { it.min(1).max(10) }
            .yRange { it.min(1).max(10) }
            .zRange { it.min(1).max(10) }
            .build(1, 1, 1)
        Assertions.assertEquals(1, build.xRange?.min)
        Assertions.assertEquals(10, build.xRange?.max)

        Assertions.assertEquals(1, build.yRange?.min)
        Assertions.assertEquals(10, build.yRange?.max)

        Assertions.assertEquals(1, build.zRange?.min)
        Assertions.assertEquals(10, build.zRange?.max)

        Assertions.assertEquals(1, build.value.x)
        Assertions.assertEquals(1, build.value.y)
        Assertions.assertEquals(1, build.value.z)
    }

    @Test
    fun vec4() {
        val build = ConfBuilder.create()
            .id("vec4.type.int")
            .name("Vec4 Conf")
            .description("This is a vec4 conf")
            .vec4<Int>()
            .xRange { it.min(1).max(10) }
            .yRange { it.min(1).max(10) }
            .zRange { it.min(1).max(10) }
            .wRange { it.min(1).max(10) }
            .build(1, 1, 1, 1)
        Assertions.assertEquals(1, build.xRange?.min)
        Assertions.assertEquals(10, build.xRange?.max)

        Assertions.assertEquals(1, build.yRange?.min)
        Assertions.assertEquals(10, build.yRange?.max)

        Assertions.assertEquals(1, build.zRange?.min)
        Assertions.assertEquals(10, build.zRange?.max)

        Assertions.assertEquals(1, build.wRange?.min)
        Assertions.assertEquals(10, build.wRange?.max)

        Assertions.assertEquals(1, build.value.x)
        Assertions.assertEquals(1, build.value.y)
        Assertions.assertEquals(1, build.value.z)
        Assertions.assertEquals(1, build.value.w)
    }

    @Test
    fun option() {
        val build = ConfBuilder.create()
            .id("option.type.int")
            .name("Option Conf")
            .description("This is a option conf")
            .option<Int>().options(mutableListOf(1, 2, 3)).build(1)
        Assertions.assertEquals(1, build.value)
    }

    internal enum class MethodType {
        METHOD1,
        METHOD2,
        METHOD3,
    }

    @Test
    fun enum() {
        val build = ConfBuilder.create()
            .id("enum.type.enum")
            .name("Enum Conf")
            .description("This is a enum conf")
            .literal<MethodType>().build(MethodType.METHOD1)
        Assertions.assertEquals(MethodType.METHOD1, build.value)
    }

    @Test
    fun requires() {
        Assertions.assertThrows(
            NullPointerException::class.java
        ) { ConfBuilder.create().literal<String>().build("") }
    }
}
