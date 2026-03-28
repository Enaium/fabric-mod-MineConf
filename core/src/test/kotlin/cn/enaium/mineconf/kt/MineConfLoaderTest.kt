package cn.enaium.mineconf.kt

import cn.enaium.mineconf.core.MineConfLoader
import org.junit.jupiter.api.Test

/**
 * @author Enaium
 */
class MineConfLoaderTest {
    @Test
    fun loader() {
        System.setProperty("user.dir", "run")
        MineConfLoader.load()
    }
}
