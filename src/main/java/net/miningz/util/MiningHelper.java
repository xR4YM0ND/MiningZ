package net.miningz.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.miningz.MiningMain;

public class MiningHelper {

    public static boolean hasRequirement(BlockState blockState) {
        return hasRequirement(blockState.getBlock());
    }

    public static boolean hasRequirement(Block block) {
        int blockId = Registries.BLOCK.getRawId(block);
        return MiningMain.BLOCK_LEVEL_MAP.containsKey(blockId);
    }

    public static boolean hasRequiredItemLevel(ItemStack item, BlockState blockState) {
        return hasRequiredItemLevel(item.getItem(), blockState.getBlock());
    }

    public static boolean hasRequiredItemLevel(Item item, Block block) {
        int blockId = Registries.BLOCK.getRawId(block);
        if (MiningMain.BLOCK_LEVEL_MAP.containsKey(blockId)) {
            int itemId = Registries.ITEM.getRawId(item);
            if (MiningMain.ITEM_LEVEL_MAP.containsKey(itemId)) {
                int itemLevel = MiningMain.ITEM_LEVEL_MAP.get(itemId);
                int blockLevel = MiningMain.BLOCK_LEVEL_MAP.get(blockId);
                return itemLevel >= blockLevel;
            }
            return false;
        }
        return true;
    }

    public static int getBlockLevel(BlockState blockState) {
        return getBlockLevel(blockState.getBlock());
    }

    public static int getBlockLevel(Block block) {
        int itemId = Registries.BLOCK.getRawId(block);
        if (MiningMain.BLOCK_LEVEL_MAP.containsKey(itemId)) {
            return MiningMain.BLOCK_LEVEL_MAP.get(itemId);
        }
        return -1;
    }

    public static int getItemLevel(ItemStack itemStack) {
        return getItemLevel(itemStack.getItem());
    }

    public static int getItemLevel(Item item) {
        int itemId = Registries.ITEM.getRawId(item);
        if (MiningMain.ITEM_LEVEL_MAP.containsKey(itemId)) {
            return MiningMain.ITEM_LEVEL_MAP.get(itemId);
        }
        return -1;
    }
}
