package cn.enaium.mineconf.kt

import cn.enaium.mineconf.MineConf
import cn.enaium.mineconf.MineConfService
import cn.enaium.mineconf.java.MineConfTest

/**
 * @author Enaium
 */
class MineConfServiceImpl : MineConfService {
    override fun conf(): MineConf {
        return MINE_CONF
    }

    companion object {
        val CONFIG: MineConfTest.Config = MineConfTest.Config()
        val MINE_CONF: MineConf = MineConf("test", "Test").register(CONFIG)
    }
}
