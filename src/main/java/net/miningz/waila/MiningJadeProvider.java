package net.miningz.waila;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.miningz.MiningClient;
import net.miningz.util.MiningHelper;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum MiningJadeProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public Identifier getUid() {
        return MiningClient.HARVESTABLE_INFO;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        int blockLevel = MiningHelper.getBlockLevel(accessor.getBlock());
        if (blockLevel >= 0) {
            if (accessor.getPlayer() != null && !accessor.getPlayer().getMainHandStack().isEmpty()) {
                Formatting formatting =
                        MiningHelper.hasRequiredItemLevel(accessor.getPlayer().getMainHandStack().getItem(), accessor.getBlock()) ? Formatting.GREEN : Formatting.RED;
                tooltip.add(Text.translatable("item.miningz.harvest_level", blockLevel).formatted(formatting));
            } else {
                tooltip.add(Text.translatable("item.miningz.harvest_level", blockLevel).formatted(Formatting.RED));
            }
        }
    }

}

