package net.miningz.init;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.miningz.data.MiningLoader;
import net.miningz.network.MiningServerPacket;
import net.miningz.util.LoaderHelper;

public class LoaderInit {

    public static void init() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(MiningLoader.ID, MiningLoader::new);
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, serverResourceManager, success) -> {
            if (success) {
                LoaderHelper.updateMiningData();
                for (int i = 0; i < server.getPlayerManager().getPlayerList().size(); i++) {
                    ServerPlayerEntity serverPlayerEntity = server.getPlayerManager().getPlayerList().get(i);
                    MiningServerPacket.writeS2CHarvestSyncPacket(serverPlayerEntity);
                }
            }
        });
    }
}
