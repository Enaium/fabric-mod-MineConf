package cn.enaium.mineconf.java;

import cn.enaium.mineconf.MineConf;
import cn.enaium.mineconf.MineConfService;

/**
 * @author Enaium
 */
public class MineConfServiceImpl implements MineConfService {

    public final static MineConfTest.Config CONFIG = new MineConfTest.Config();
    public final static MineConf MINE_CONF = new MineConf("test", "Test").register(CONFIG);

    @Override
    public MineConf conf() {
        return MINE_CONF;
    }
}
