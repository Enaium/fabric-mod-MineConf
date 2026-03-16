package cn.enaium.mineconf.kt

import cn.enaium.mineconf.ConfBuilder
import cn.enaium.mineconf.MineConf
import cn.enaium.mineconf.annotation.ConfField
import cn.enaium.mineconf.conf.*
import cn.enaium.mineconf.type.Vec2
import cn.enaium.mineconf.type.Vec3
import cn.enaium.mineconf.type.Vec4
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
        Assertions.assertEquals("Hello World!", out.literalString.getValue())
        Assertions.assertEquals(Long.MAX_VALUE, out.literalLong.getValue())
        Assertions.assertEquals(Int.MAX_VALUE, out.literalInteger.getValue())
        Assertions.assertEquals(Short.MAX_VALUE, out.literalShort.getValue())
        Assertions.assertEquals(Byte.MAX_VALUE, out.literalByte.getValue())
        Assertions.assertEquals(true, out.literalBoolean.getValue())
        Assertions.assertEquals(Double.MAX_VALUE, out.literalDouble.getValue())
        Assertions.assertEquals(Float.MAX_VALUE, out.literalFloat.getValue())
        Assertions.assertEquals("Option 3", out.optionString.getValue())
        Assertions.assertEquals(Vec2(0, 0), out.vec2Conf.getValue())
        Assertions.assertEquals(Vec3(0, 0, 0), out.vec3Conf.getValue())
        Assertions.assertEquals(Vec4(0, 0, 0, 0), out.vec4Conf.getValue())
        Assertions.assertArrayEquals(
            arrayOf("1", "2", "3"),
            out.collectionConf.getValue().toTypedArray<String>()
        )
    }

    class Config {
        @ConfField
        var literalString
                : Conf<String> = ConfBuilder.create()
            .id("literal.type.string")
            .name("String Conf")
            .description("This is a string conf")
            .literal()
            .build<String>("Hello World!")

        @ConfField
        var literalLong
                : Conf<Long> = ConfBuilder.create()
            .id("literal.type.long")
            .name("Long Conf")
            .description("This is a string conf")
            .literal()
            .build(Long.MAX_VALUE)

        @ConfField
        var literalInteger
                : Conf<Int> = ConfBuilder.create()
            .id("literal.type.integer")
            .name("Integer Conf")
            .description("This is an integer conf")
            .literal()
            .build(Int.MAX_VALUE)

        @ConfField
        var literalShort
                : Conf<Short> = ConfBuilder.create()
            .id("literal.type.short")
            .name("Short Conf")
            .description("This is a short conf")
            .literal()
            .build(Short.MAX_VALUE)

        @ConfField
        var literalByte
                : Conf<Byte> = ConfBuilder.create()
            .id("literal.type.byte")
            .name("Byte Conf")
            .description("This is a byte conf")
            .literal()
            .build(Byte.MAX_VALUE)

        @ConfField
        var literalBoolean
                : Conf<Boolean> = ConfBuilder.create()
            .id("literal.type.boolean")
            .name("Boolean Conf")
            .description("This is a boolean conf")
            .literal()
            .build(true)

        @ConfField
        var literalDouble
                : Conf<Double> = ConfBuilder.create()
            .id("literal.type.double")
            .name("Double Conf")
            .description("This is a double conf")
            .literal()
            .build(Double.MAX_VALUE)

        @ConfField
        var literalFloat
                : Conf<Float> = ConfBuilder.create()
            .id("literal.type.float")
            .name("Float Conf")
            .description("This is a float conf")
            .literal()
            .build(Float.MAX_VALUE)

        @ConfField
        var optionString
                : OptionConf<String> = ConfBuilder.create()
            .id("option.type.string")
            .name("String Conf")
            .description("This is a string conf")
            .option<Any>()
            .options(mutableListOf<Any>("Option 1", "Option 2", "Option 3"))
            .build("Option 3")

        @ConfField
        var vec2Conf
                : Vec2Conf<Int> = ConfBuilder.create()
            .id("vec2.type.integer")
            .name("Vec2 Conf")
            .description("This is a vec2 conf")
            .vec2<Number>().build(0, 0)

        @ConfField
        var vec3Conf
                : Vec3Conf<Int> = ConfBuilder.create()
            .id("vec3.type.integer")
            .name("Vec3 Conf")
            .description("This is a vec3 conf")
            .vec3<Number>().build(0, 0, 0)


        @ConfField
        var vec4Conf
                : Vec4Conf<Int> = ConfBuilder.create()
            .id("vec4.type.integer")
            .name("Vec4 Conf")
            .description("This is a vec4 conf")
            .vec4<Number>().build(0, 0, 0, 0)

        @ConfField
        var collectionConf: CollectionConf<String> = ConfBuilder.create()
            .id("collection.type.string")
            .name("Collection Conf")
            .description("This is a collection conf")
            .build(mutableListOf("1", "2", "3"))

        @ConfField
        var oneToManyConf: OneToManyConf<String, String> = ConfBuilder.create()
            .id("one-to-many.type.string")
            .name("One-to-Many Conf")
            .description("This is a one-to-many conf")
            .build(
                mutableMapOf(
                    "1" to mutableListOf("1-1", "1-2", "1-3"),
                    "2" to mutableListOf("2-1", "2-2", "2-3")
                )
            )
    }
}
