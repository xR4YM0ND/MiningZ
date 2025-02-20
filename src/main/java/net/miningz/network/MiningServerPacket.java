package net.miningz.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.miningz.MiningMain;
import net.miningz.network.packet.HarvestSyncPacket;

public class MiningServerPacket {

    public static void init() {
        PayloadTypeRegistry.playS2C().register(HarvestSyncPacket.PACKET_ID, HarvestSyncPacket.PACKET_CODEC);
    }

    public static void writeS2CHarvestSyncPacket(ServerPlayerEntity serverPlayerEntity) {
        ServerPlayNetworking.send(serverPlayerEntity, new HarvestSyncPacket(MiningMain.ITEM_LEVEL_MAP, MiningMain.BLOCK_LEVEL_MAP));
    }
}
