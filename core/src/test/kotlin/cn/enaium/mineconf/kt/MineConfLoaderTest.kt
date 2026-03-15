package cn.enaium.mineconf.kt

import cn.enaium.mineconf.MineConfLoader
import org.junit.jupiter.api.Test

/**
 * @author Enaium
 */
class MineConfLoaderTest {
    @Test
    fun loader() {
        MineConfLoader.load()
        Runtime.getRuntime().addShutdownHook(Thread(MineConfLoader::save))
    }
}
