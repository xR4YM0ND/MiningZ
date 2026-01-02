package net.miningz.init;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.miningz.MiningMain;

public class TagInit {

    public static final TagKey<Block> PROSPECTOR_BLOCKS = TagKey.of(RegistryKeys.BLOCK, MiningMain.identifierOf("prospector_blocks"));

    public static void init() {
    }
}
