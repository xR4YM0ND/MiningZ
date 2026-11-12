package net.miningz.init;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.miningz.network.MiningServerPacket;
import net.miningz.util.LoaderHelper;

public class EventInit {

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            LoaderHelper.updateMiningData();
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            MiningServerPacket.writeS2CHarvestSyncPacket(handler.getPlayer());
        });
    }
}
