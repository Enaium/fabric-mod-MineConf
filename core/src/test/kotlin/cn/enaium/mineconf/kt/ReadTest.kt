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
package cn.enaium.mineconf.kt

import cn.enaium.mineconf.ConfBuilder
import cn.enaium.mineconf.MineConf
import cn.enaium.mineconf.annotation.ConfField
import cn.enaium.mineconf.conf.Conf
import com.fasterxml.jackson.core.JsonProcessingException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Enaium
 */
class ReadTest {
    @Test
    @Throws(JsonProcessingException::class)
    fun read() {
        val instance = TestConfig()
        val mineConf = MineConf("test", "Test", instance)
        mineConf.read(
            "{\n" +
                    "  \"literal.type.string\": {\n" +
                    "    \"name\": \"String Conf\",\n" +
                    "    \"description\": \"This is a string conf\",\n" +
                    "    \"value\": \"Hello World!\"\n" +
                    "  }\n" +
                    "}"
        )
        Assertions.assertEquals("Hello World!", instance.string.value)
        Assertions.assertEquals("Good Name", instance.string.name)
        Assertions.assertEquals("Good Description", instance.string.description)
    }

    internal class TestConfig {
        @ConfField
        var string: Conf<String> = ConfBuilder.create()
            .id("literal.type.string")
            .name("Good Name")
            .description("Good Description")
            .literal<String>().build("Goodbye World!")
    }
}
