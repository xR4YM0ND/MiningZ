package net.miningz.init;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.miningz.util.LoaderHelper;

public class EventInit {

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            LoaderHelper.updateMiningData();
        });
    }
}
