package net.miningz.waila;

import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.miningz.MiningClient;
import net.miningz.util.MiningHelper;

public class MiningWTHITProvider implements IBlockComponentProvider {

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        IBlockComponentProvider.super.appendBody(tooltip, accessor, config);
        if (config.getBoolean(MiningClient.HARVESTABLE_INFO)) {
            int blockLevel = MiningHelper.getBlockLevel(accessor.getBlock());
            if (blockLevel >= 0) {
                if (accessor.getPlayer() != null && !accessor.getPlayer().getMainHandStack().isEmpty()) {
                    Formatting formatting =
                            MiningHelper.hasRequiredItemLevel(accessor.getPlayer().getMainHandStack().getItem(), accessor.getBlock()) ? Formatting.GREEN : Formatting.RED;
                    tooltip.addLine(Text.translatable("item.miningz.harvest_level", blockLevel).formatted(formatting));
                } else {
                    tooltip.addLine(Text.translatable("item.miningz.harvest_level", blockLevel).formatted(Formatting.RED));
                }
            }
        }
    }
}
