package com.samoyed24.minternet.tags;

import com.samoyed24.minternet.Minternet;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> CAN_BE_SHAPED_BY_IRON_TOOL =
            of("can_be_shaped_by_iron_tool");
    public static final TagKey<Item> CAN_BE_SHAPED_BY_DIAMOND_TOOL =
            of("can_be_shaped_by_diamond_tool");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(Minternet.MOD_ID, id));
    }
    public static void registerModItemTags() {
        Minternet.LOGGER.info("Registering ModItemTags");
    }
}
