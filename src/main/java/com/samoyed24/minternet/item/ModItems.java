package com.samoyed24.minternet.item;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.item.custom.Resin;
import com.samoyed24.minternet.item.custom.ResinTappingKnifeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item NETWORK_CARD = registerItems("network_card", new Item(new Item.Settings()));
    public static final Item RAW_TIN = registerItems("raw_tin", new Item(new Item.Settings()));
    public static final Item TIN_INGOT = registerItems("tin_ingot", new Item(new Item.Settings()));
    public static final Item TIN_NUGGET = registerItems("tin_nugget", new Item(new Item.Settings()));
    public static final Item RESIN = registerItems("resin", new Resin(new Item.Settings().food(ModFoodComponents.RESIN)));
    public static final Item IRON_RESIN_TAPPING_KNIFE = registerItems("iron_resin_tapping_knife", new ResinTappingKnifeItem(new Item.Settings().maxDamage(128)));
    public static final Item DIAMOND_RESIN_TAPPING_KNIFE = registerItems("diamond_resin_tapping_knife", new ResinTappingKnifeItem(new Item.Settings().maxDamage(700)));
    public static final Item ROSIN = registerItems("rosin", new Item(new Item.Settings()));

    private static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Minternet.MOD_ID, id), item);
    }
    public static void registerModItems() {
        Minternet.LOGGER.info("Registering Mod Items");
    }
}
