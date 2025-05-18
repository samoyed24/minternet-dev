package com.samoyed24.minternet.item;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.item.custom.DiagonalPliersItem;
import com.samoyed24.minternet.item.custom.HammerItem;
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
    public static final Item IRON_HAMMER = registerItems("iron_hammer", new HammerItem(new Item.Settings().maxDamage(64)));
    public static final Item DIAMOND_HAMMER = registerItems("diamond_hammer", new HammerItem(new Item.Settings().maxDamage(384)));
    public static final Item DIAMOND_DIAGONAL_PLIERS = registerItems("diamond_diagonal_pliers", new DiagonalPliersItem(new Item.Settings().maxDamage(384)));
    public static final Item IRON_DIAGONAL_PLIERS = registerItems("iron_diagonal_pliers", new DiagonalPliersItem(new Item.Settings().maxDamage(64)));
    public static final Item IRON_PLATE = registerItems("iron_plate", new Item(new Item.Settings()));
    public static final Item DIAMOND_PLATE = registerItems("diamond_plate", new Item(new Item.Settings()));
    public static final Item TIN_PLATE = registerItems("tin_plate", new Item(new Item.Settings()));
    public static final Item COPPER_PLATE = registerItems("copper_plate", new Item(new Item.Settings()));
    public static final Item CRUDE_TIN_ROD = registerItems("crude_tin_rod", new Item(new Item.Settings()));
    public static final Item CRUDE_IRON_ROD = registerItems("crude_iron_rod", new Item(new Item.Settings()));
    public static final Item CRUDE_COPPER_ROD = registerItems("crude_copper_rod", new Item(new Item.Settings()));
    public static final Item TIN_WIRE = registerItems("tin_wire", new Item(new Item.Settings()));
    public static final Item IRON_WIRE = registerItems("iron_wire", new Item(new Item.Settings()));
    public static final Item COPPER_WIRE = registerItems("copper_wire", new Item(new Item.Settings()));
    public static final Item SOLDER_WIRE = registerItems("solder_wire", new Item(new Item.Settings().maxDamage(64)));
    private static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Minternet.MOD_ID, id), item);
    }
    public static void registerModItems() {
        Minternet.LOGGER.info("Registering Mod Items");
    }
}
