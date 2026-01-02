package net.miningz.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.miningz.access.ClientPlayerEntityAccess;

import java.util.List;

@Environment(EnvType.CLIENT)
public class RenderInit {

    public static void init() {
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && !((ClientPlayerEntityAccess) client.player).miningZ$getProspectorBlocks().isEmpty()) {
                List<Identifier> blockIds = ((ClientPlayerEntityAccess) client.player).miningZ$getProspectorBlocks();
                for (int i = 0; i < blockIds.size(); i++) {
                    Item item = Registries.ITEM.get(blockIds.get(i));
                    int x = context.getScaledWindowWidth() - 24 + ConfigInit.CONFIG.prospectorX;
                    int y = context.getScaledWindowHeight() - 16 - 18 * i - 8 + ConfigInit.CONFIG.prospectorY;
                    context.drawItem(item.getDefaultStack(), x, y);
                    context.drawText(client.textRenderer, item.getName(), x - 4 - client.textRenderer.getWidth(item.getName()), y + 3, 0xFFFFFF, false);
                }
            }
        });
    }

}
