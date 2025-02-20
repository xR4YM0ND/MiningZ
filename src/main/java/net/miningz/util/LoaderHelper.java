package net.miningz.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.miningz.MiningMain;
import net.miningz.data.MiningLoader;

import java.util.Map;

public class LoaderHelper {

    public static void updateMiningData() {
        for (Map.Entry<Integer, TagKey<Item>> entry : MiningLoader.ITEM_TAG_MAP.entrySet()) {
            for (RegistryEntry<Item> item : Registries.ITEM.iterateEntries(entry.getValue())) {
                MiningMain.ITEM_LEVEL_MAP.put(Registries.ITEM.getRawId(item.value()), entry.getKey());
            }
        }

        for (Map.Entry<Integer, TagKey<Block>> entry : MiningLoader.BLOCK_TAG_MAP.entrySet()) {
            for (RegistryEntry<Block> block : Registries.BLOCK.iterateEntries(entry.getValue())) {
                MiningMain.BLOCK_LEVEL_MAP.put(Registries.BLOCK.getRawId(block.value()), entry.getKey());
            }
        }
        MiningLoader.BLOCK_TAG_MAP.clear();
        MiningLoader.ITEM_TAG_MAP.clear();
    }

}
