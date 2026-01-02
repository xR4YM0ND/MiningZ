package net.miningz;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.miningz.init.*;
import net.miningz.network.MiningServerPacket;

import java.util.HashMap;
import java.util.Map;

public class MiningMain implements ModInitializer {

    public static final Map<Integer, Integer> BLOCK_LEVEL_MAP = new HashMap<>();
    public static final Map<Integer, Integer> ITEM_LEVEL_MAP = new HashMap<>();

    @Override
    public void onInitialize() {
        ConfigInit.init();
        RuleInit.init();
        ItemInit.init();
        TagInit.init();
        LoaderInit.init();
        EventInit.init();
        MiningServerPacket.init();
    }

    public static Identifier identifierOf(String name) {
        return Identifier.of("miningz", name);
    }
}
