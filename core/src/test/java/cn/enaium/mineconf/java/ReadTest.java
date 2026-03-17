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

package cn.enaium.mineconf.java;

import cn.enaium.mineconf.ConfBuilder;
import cn.enaium.mineconf.MineConf;
import cn.enaium.mineconf.annotation.ConfField;
import cn.enaium.mineconf.conf.Conf;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Enaium
 */
public class ReadTest {
    @Test
    public void read() throws JsonProcessingException {
        final TestConfig instance = new TestConfig();
        MineConf mineConf = new MineConf("test", "Test", instance);
        mineConf.read("{\n" +
                "  \"literal.type.string\": {\n" +
                "    \"name\": \"Bad Name\",\n" +
                "    \"description\": \"Bad Description\",\n" +
                "    \"value\": \"Hello World!\"\n" +
                "  }\n" +
                "}");
        assertEquals("Hello World!", instance.string.getValue());
        assertEquals("Good Name", instance.string.getName());
        assertEquals("Good Description", instance.string.getDescription());
    }

    static class TestConfig {
        @ConfField
        Conf<String> string = ConfBuilder.create()
                .id("literal.type.string")
                .name("Good Name")
                .description("Good Description")
                .<String>literal().build("Goodbye World!");
    }
}
