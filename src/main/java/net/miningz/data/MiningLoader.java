package net.miningz.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.miningz.MiningMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record MiningLoader(RegistryWrapper.WrapperLookup wrapperLookup) implements SimpleSynchronousResourceReloadListener {

    public static final Identifier ID = MiningMain.identifierOf("miningz");

    public static final Map<Integer, TagKey<Block>> BLOCK_TAG_MAP = new HashMap<>();
    public static final Map<Integer, TagKey<Item>> ITEM_TAG_MAP = new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger("MiningZ");

    private static final List<Integer> replaceList = new ArrayList<>();

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public void reload(ResourceManager manager) {
        manager.findResources("mining", id -> id.getPath().endsWith(".json")).forEach((id, resourceRef) -> {
            try {
                InputStream stream = resourceRef.getInputStream();
                JsonObject data = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();

                for (int i = 0; i <= 100; i++) {
                    JsonElement jsonElement = data.get(String.valueOf(i));

                    if (jsonElement instanceof JsonObject jsonObject) {
                        if (replaceList.contains(i)) {
                            continue;
                        }
                        if (JsonHelper.getBoolean(jsonObject, "replace", false)) {
                            replaceList.add(i);
                            for (Map.Entry<Integer, Integer> entry : MiningMain.ITEM_LEVEL_MAP.entrySet()) {
                                if (entry.getValue() == i) {
                                    MiningMain.ITEM_LEVEL_MAP.remove(entry.getKey());
                                }
                            }
                            for (Map.Entry<Integer, TagKey<Item>> entry : ITEM_TAG_MAP.entrySet()) {
                                if (entry.getKey() == i) {
                                    ITEM_TAG_MAP.remove(entry.getKey());
                                }
                            }
                            for (Map.Entry<Integer, Integer> entry : MiningMain.BLOCK_LEVEL_MAP.entrySet()) {
                                if (entry.getValue() == i) {
                                    MiningMain.BLOCK_LEVEL_MAP.remove(entry.getKey());
                                }
                            }
                            for (Map.Entry<Integer, TagKey<Block>> entry : BLOCK_TAG_MAP.entrySet()) {
                                if (entry.getKey() == i) {
                                    BLOCK_TAG_MAP.remove(entry.getKey());
                                }
                            }
                        }
                        if (jsonObject.getAsJsonArray("items") != null) {
                            for (int u = 0; u < jsonObject.getAsJsonArray("items").size(); u++) {
                                String itemId = jsonObject.getAsJsonArray("items").get(u).getAsString();
                                if (itemId.startsWith("#")) {
                                    itemId = itemId.replace("#", "");

                                    boolean foundTag = false;
                                    for (TagKey<Item> tagKey : Registries.ITEM.streamTags().toList()) {
                                        if (tagKey.id().toString().equals(itemId)) {
                                            ITEM_TAG_MAP.put(i, tagKey);
                                            foundTag = true;
                                            break;
                                        }
                                    }
                                    if (!foundTag) {
                                        LOGGER.warn("{} contains an unrecognized tag id called {}.", id.toString(), itemId);
                                        continue;
                                    }
                                } else {
                                    Identifier itemIdentifier = Identifier.of(itemId);
                                    if (Registries.ITEM.containsId(itemIdentifier)) {
                                        int itemRawId = Registries.ITEM.getRawId(Registries.ITEM.get(itemIdentifier));
                                        MiningMain.ITEM_LEVEL_MAP.put(itemRawId, i);
                                    } else {
                                        LOGGER.warn("{} contains an unrecognized item id called {}.", id.toString(), itemIdentifier);
                                    }
                                }
                            }
                        }
                        if (jsonObject.getAsJsonArray("blocks") != null) {
                            for (int u = 0; u < jsonObject.getAsJsonArray("blocks").size(); u++) {
                                String blockId = jsonObject.getAsJsonArray("blocks").get(u).getAsString();
                                if (blockId.startsWith("#")) {
                                    blockId = blockId.replace("#", "");

                                    boolean foundTag = false;
                                    for (TagKey<Block> tagKey : Registries.BLOCK.streamTags().toList()) {
                                        if (tagKey.id().toString().equals(blockId)) {
                                            BLOCK_TAG_MAP.put(i, tagKey);
                                            foundTag = true;
                                            break;
                                        }
                                    }
                                    if (!foundTag) {
                                        LOGGER.warn("{} contains an unrecognized tag id called {}.", id.toString(), blockId);
                                        continue;
                                    }
                                } else {
                                    Identifier blockIdentifier = Identifier.of(blockId);
                                    if (Registries.BLOCK.containsId(blockIdentifier)) {
                                        int blockRawId = Registries.BLOCK.getRawId(Registries.BLOCK.get(blockIdentifier));
                                        MiningMain.BLOCK_LEVEL_MAP.put(blockRawId, i);
                                    } else {
                                        LOGGER.warn("{} contains an unrecognized item id called {}.", id.toString(), blockIdentifier);
                                    }
                                }
                            }
                        }
                    }

                }
            } catch (Exception e) {
                LOGGER.error("Error occurred while loading resource {}. {}", id.toString(), e.toString());
            }
        });
    }
}
