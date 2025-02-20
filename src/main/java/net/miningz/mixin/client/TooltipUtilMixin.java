package net.miningz.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.levelz.level.LevelManager;
import net.levelz.util.TooltipUtil;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.HitResult;
import net.miningz.util.MiningHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(TooltipUtil.class)
public class TooltipUtilMixin {

    @Inject(method = "renderTooltip(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/levelz/level/LevelManager;hasRequiredMiningLevel(Lnet/minecraft/block/Block;)Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void renderTooltipMixin(MinecraftClient client, DrawContext context, CallbackInfo info, HitResult hitResult, Block block, LevelManager levelManager, List<Text> textList) {
        int blockLevel = MiningHelper.getBlockLevel(block);
        if (blockLevel >= 0) {
            if (client.player != null && !client.player.getMainHandStack().isEmpty()) {
                Formatting formatting =
                        MiningHelper.hasRequiredItemLevel(client.player.getMainHandStack().getItem(), block) ? Formatting.GREEN : Formatting.RED;
                textList.add(Text.translatable("item.miningz.harvest_level", blockLevel).formatted(formatting));
            } else {
                textList.add(Text.translatable("item.miningz.harvest_level", blockLevel).formatted(Formatting.RED));
            }
        }
    }
}
