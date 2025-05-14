package com.samoyed24.minternet.item;

import com.samoyed24.minternet.Minternet;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.item.MinecartItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SERVER = registerItems("server", new Item(new Item.Settings()));
    public static final Item ROUTER = registerItems("router", new Item(new Item.Settings()));
    public static final Item NETWORK_CARD = registerItems("network_card", new Item(new Item.Settings()));
    private static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Minternet.MOD_ID, id), item);
    }
    public static void registerModItems() {
        Minternet.LOGGER.info("Registering Mod Items");
    }
}
