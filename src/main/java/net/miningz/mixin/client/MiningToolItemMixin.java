package net.miningz.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.miningz.util.MiningHelper;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(MiningToolItem.class)
public abstract class MiningToolItemMixin extends ToolItem {

    public MiningToolItemMixin(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        int itemLevel = MiningHelper.getItemLevel(stack);
        if (itemLevel >= 0) {
            tooltip.add(Text.translatable("item.miningz.harvest_level", itemLevel).formatted(Formatting.DARK_GREEN));
        }
    }
}
