package net.miningz.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.miningz.config.MiningConfig;

public class ConfigInit {

    public static MiningConfig CONFIG = new MiningConfig();

    public static void init() {
        AutoConfig.register(MiningConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(MiningConfig.class).getConfig();
    }
}
