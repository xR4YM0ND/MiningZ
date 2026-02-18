package net.miningz.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "miningz")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class MiningConfig implements ConfigData {

    @Comment("Screen position for prospector information")
    public int prospectorX = 0;
    public int prospectorY = 0;

}