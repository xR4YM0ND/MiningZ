package net.miningz.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.miningz.MiningMain;
import net.miningz.network.packet.HarvestSyncPacket;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class MiningClientPacket {

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(HarvestSyncPacket.PACKET_ID, (payload, context) -> {
            Map<Integer, Integer> itemLevelMap = payload.itemLevelMap();
            Map<Integer, Integer> blockLevelMap = payload.blockLevelMap();

            context.client().execute(() -> {
                MiningMain.ITEM_LEVEL_MAP.clear();
                MiningMain.BLOCK_LEVEL_MAP.clear();
                MiningMain.ITEM_LEVEL_MAP.putAll(itemLevelMap);
                MiningMain.BLOCK_LEVEL_MAP.putAll(blockLevelMap);
            });
        });
    }
}
