package net.miningz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.miningz.network.MiningClientPacket;

@Environment(EnvType.CLIENT)
public class MiningClient implements ClientModInitializer {

    public static final Identifier HARVESTABLE_INFO = MiningMain.identifierOf("harvestable_info");

    @Override
    public void onInitializeClient() {
        MiningClientPacket.init();
    }
}
