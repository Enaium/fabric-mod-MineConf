package cn.enaium.mineconf.java;

import cn.enaium.mineconf.MineConfLoader;
import org.junit.jupiter.api.Test;

/**
 * @author Enaium
 */
public class MineConfLoaderTest {
    @Test
    public void loader() {
        MineConfLoader.load();
        Runtime.getRuntime().addShutdownHook(new Thread(MineConfLoader::save));
    }
}
