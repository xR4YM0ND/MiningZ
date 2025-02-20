package net.miningz.network.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.miningz.MiningMain;

import java.util.Map;

public record HarvestSyncPacket(Map<Integer, Integer> itemLevelMap, Map<Integer, Integer> blockLevelMap) implements CustomPayload {

    public static final CustomPayload.Id<HarvestSyncPacket> PACKET_ID = new CustomPayload.Id<>(MiningMain.identifierOf("harvest_packet"));

    public static final PacketCodec<RegistryByteBuf, HarvestSyncPacket> PACKET_CODEC = PacketCodec.of((value, buf) -> {
        buf.writeMap(value.itemLevelMap, PacketByteBuf::writeInt, PacketByteBuf::writeInt);
        buf.writeMap(value.blockLevelMap, PacketByteBuf::writeInt, PacketByteBuf::writeInt);
    }, buf -> new HarvestSyncPacket(buf.readMap(PacketByteBuf::readInt, PacketByteBuf::readInt), buf.readMap(PacketByteBuf::readInt, PacketByteBuf::readInt)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

}


