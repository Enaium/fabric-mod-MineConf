package cn.enaium.mineconf.kt

import cn.enaium.mineconf.core.ConfBuilder
import cn.enaium.mineconf.core.MineConf
import cn.enaium.mineconf.core.conf.*
import cn.enaium.mineconf.core.type.Vec2
import cn.enaium.mineconf.core.type.Vec3
import cn.enaium.mineconf.core.type.Vec4
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Enaium
 */
class MineConfTest {
    @Test
    fun mineConf() {
        val conf = MineConf("test", "Test", Config())
        val write = conf.write()
        conf.read(write)
        val out = conf.getConf(Config())
        Assertions.assertEquals("Hello World!", out.literalString.value)
        Assertions.assertEquals(Long.MAX_VALUE, out.literalLong.value)
        Assertions.assertEquals(Int.MAX_VALUE, out.literalInteger.value)
        Assertions.assertEquals(Short.MAX_VALUE, out.literalShort.value)
        Assertions.assertEquals(Byte.MAX_VALUE, out.literalByte.value)
        Assertions.assertEquals(true, out.literalBoolean.value)
        Assertions.assertEquals(Double.MAX_VALUE, out.literalDouble.value)
        Assertions.assertEquals(Float.MAX_VALUE, out.literalFloat.value)
        Assertions.assertEquals("Option 3", out.optionString.value)
        Assertions.assertEquals(Vec2(0, 0), out.vec2Conf.value)
        Assertions.assertEquals(Vec3(0, 0, 0), out.vec3Conf.value)
        Assertions.assertEquals(Vec4(0, 0, 0, 0), out.vec4Conf.value)
        Assertions.assertArrayEquals(
            arrayOf("1", "2", "3"),
            out.collectionConf.value.toTypedArray<String>()
        )
    }

    class Config {
        var literalString
                : Conf<String> = ConfBuilder.create()
            .id("literal.type.string")
            .name("String Conf")
            .description("This is a string conf")
            .literal<String>()
            .build("Hello World!")

        var literalLong
                : Conf<Long> = ConfBuilder.create()
            .id("literal.type.long")
            .name("Long Conf")
            .description("This is a string conf")
            .literal<Long>()
            .build(Long.MAX_VALUE)

        var literalInteger: Conf<Int> = ConfBuilder.create()
            .id("literal.type.integer")
            .name("Integer Conf")
            .description("This is an integer conf")
            .literal<Int>()
            .build(Int.MAX_VALUE)

        var literalShort: Conf<Short> = ConfBuilder.create()
            .id("literal.type.short")
            .name("Short Conf")
            .description("This is a short conf")
            .literal<Short>()
            .build(Short.MAX_VALUE)

        var literalByte
                : Conf<Byte> = ConfBuilder.create()
            .id("literal.type.byte")
            .name("Byte Conf")
            .description("This is a byte conf")
            .literal<Byte>()
            .build(Byte.MAX_VALUE)

        var literalBoolean
                : Conf<Boolean> = ConfBuilder.create()
            .id("literal.type.boolean")
            .name("Boolean Conf")
            .description("This is a boolean conf")
            .literal<Boolean>()
            .build(true)

        var literalDouble
                : Conf<Double> = ConfBuilder.create()
            .id("literal.type.double")
            .name("Double Conf")
            .description("This is a double conf")
            .literal<Double>()
            .build(Double.MAX_VALUE)

        var literalFloat
                : Conf<Float> = ConfBuilder.create()
            .id("literal.type.float")
            .name("Float Conf")
            .description("This is a float conf")
            .literal<Float>()
            .build(Float.MAX_VALUE)

        var optionString: OptionConf<String> = ConfBuilder.create()
            .id("option.type.string")
            .name("String Conf")
            .description("This is a string conf")
            .option<Any>()
            .options(mutableListOf<Any>("Option 1", "Option 2", "Option 3"))
            .build("Option 3")

        var vec2Conf: Vec2Conf<Int> = ConfBuilder.create()
            .id("vec2.type.integer")
            .name("Vec2 Conf")
            .description("This is a vec2 conf")
            .vec2<Int>().build(0, 0)

        var vec3Conf
                : Vec3Conf<Int> = ConfBuilder.create()
            .id("vec3.type.integer")
            .name("Vec3 Conf")
            .description("This is a vec3 conf")
            .vec3<Int>().build(0, 0, 0)


        var vec4Conf
                : Vec4Conf<Int> = ConfBuilder.create()
            .id("vec4.type.integer")
            .name("Vec4 Conf")
            .description("This is a vec4 conf")
            .vec4<Int>().build(0, 0, 0, 0)

        var collectionConf: CollectionConf<String> = ConfBuilder.create()
            .id("collection.type.string")
            .name("Collection Conf")
            .description("This is a collection conf")
            .collection<String>()
            .build(mutableListOf("1", "2", "3"))

        var multimapConf: MultimapConf<String, String> = ConfBuilder.create()
            .id("one-to-many.type.string")
            .name("One-to-Many Conf")
            .description("This is a one-to-many conf")
            .multimap<String, String>()
            .build(
                mutableMapOf(
                    "1" to mutableListOf("1-1", "1-2", "1-3"),
                    "2" to mutableListOf("2-1", "2-2", "2-3")
                )
            )
    }
}
