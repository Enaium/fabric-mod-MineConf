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

package cn.enaium.mineconf;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author Enaium
 */
public class MineConfLoader {
    public static final Map<String, MineConf> MINE_CONF = new HashMap<>();

    public static Path path(String name) {
        return Paths.get(System.getProperty("user.dir")).resolve("MineConf").resolve(name + ".json");
    }

    public static void load() {
        for (MineConfService mineConfService : ServiceLoader.load(MineConfService.class)) {
            final MineConf conf = mineConfService.conf();
            final Path path = path(conf.getName());
            if (Files.exists(path)) {
                try {
                    conf.read(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
                } catch (IOException e) {
                    throw new RuntimeException("Unable to load config file: " + path, e);
                }
            } else {
                save(conf);
            }
            MINE_CONF.put(conf.getId(), conf);
        }
    }

    public static void save(MineConf conf) {
        final Path path = path(conf.getName());
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, conf.write().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Unable to save config file: " + path, e);
        }
    }

    public static void save() {
        for (Map.Entry<String, MineConf> stringMineConfEntry : MINE_CONF.entrySet()) {
            save(stringMineConfEntry.getValue());
        }
    }
}
