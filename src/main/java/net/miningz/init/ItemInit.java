package net.miningz.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.miningz.MiningMain;
import net.miningz.item.ProspectorPick;

public class ItemInit {

    public static final RegistryKey<ItemGroup> MININGZ_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, MiningMain.identifierOf("item_group"));

    // public static final Item WOODEN_PROSPECTOR_PICK = register("wooden_prospector_pick", new ProspectorPick(3, new Item.Settings().maxDamage(59)), MININGZ_ITEM_GROUP);
    // public static final Item STONE_PROSPECTOR_PICK = register("stone_prospector_pick", new ProspectorPick(4, new Item.Settings().maxDamage(131)), MININGZ_ITEM_GROUP);
    public static final Item COPPER_PROSPECTOR_PICK = register("copper_prospector_pick", new ProspectorPick(5, new Item.Settings().maxDamage(190)), MININGZ_ITEM_GROUP);
    public static final Item GOLDEN_PROSPECTOR_PICK = register("golden_prospector_pick", new ProspectorPick(6, new Item.Settings().maxDamage(32)), MININGZ_ITEM_GROUP);
    public static final Item IRON_PROSPECTOR_PICK = register("iron_prospector_pick", new ProspectorPick(7, new Item.Settings().maxDamage(250)), MININGZ_ITEM_GROUP);
    // public static final Item STEEL_PROSPECTOR_PICK = register("steel_prospector_pick", new ProspectorPick(8, new Item.Settings().maxDamage(641)), MININGZ_ITEM_GROUP);
    public static final Item DIAMOND_PROSPECTOR_PICK = register("diamond_prospector_pick", new ProspectorPick(9, new Item.Settings().maxDamage(1561)), MININGZ_ITEM_GROUP);
    public static final Item NETHERITE_PROSPECTOR_PICK = register("netherite_prospector_pick", new ProspectorPick(10, new Item.Settings().maxDamage(2031)), MININGZ_ITEM_GROUP);

    private static Item register(String id, Item item, RegistryKey<ItemGroup> itemGroup) {
        return register(MiningMain.identifierOf(id), item, itemGroup);
    }

    private static Item register(Identifier id, Item item, RegistryKey<ItemGroup> itemGroup) {
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, MININGZ_ITEM_GROUP,
                FabricItemGroup.builder().icon(() -> new ItemStack(ItemInit.NETHERITE_PROSPECTOR_PICK)).displayName(Text.translatable("item.miningz.item_group")).build());
    }
}
