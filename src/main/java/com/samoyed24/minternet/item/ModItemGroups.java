package com.samoyed24.minternet.item;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup NETWORK = Registry.register(Registries.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, "network"),
            ItemGroup.create(ItemGroup.Row.TOP, 6)
                    .displayName(Text.translatable("itemGroup.minternet.network"))
                    .icon(() -> new ItemStack(ModItems.NETWORK_CARD))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.NETWORK_CARD);
                        entries.add(ModBlocks.SERVER);
                        entries.add(ModBlocks.ROUTER);
                    })
                    .build());

    public static final ItemGroup MINERAL = Registry.register(Registries.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, "mineral"),
            ItemGroup.create(ItemGroup.Row.TOP, 7)
                    .displayName(Text.translatable("itemGroup.minternet.mineral"))
                    .icon(() -> new ItemStack(ModItems.TIN_INGOT))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TIN_INGOT);
                        entries.add(ModBlocks.TIN_BLOCK);
                        entries.add(ModItems.RAW_TIN);
                        entries.add(ModItems.TIN_NUGGET);
                        entries.add(ModBlocks.TIN_ORE);
                    }).build());

    public static final ItemGroup NATURAL = Registry.register(Registries.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, "natural"),
            ItemGroup.create(ItemGroup.Row.TOP, 8)
                    .displayName(Text.translatable("itemGroup.minternet.natural"))
                    .icon(() -> new ItemStack(ModItems.RESIN))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.RESIN);
                    }).build());

    public static final ItemGroup TOOL = Registry.register(Registries.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, "tool"),
            ItemGroup.create(ItemGroup.Row.TOP, 9)
                    .displayName(Text.translatable("itemGroup.minternet.tool"))
                    .icon(() -> new ItemStack(ModItems.DIAMOND_RESIN_TAPPING_KNIFE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.IRON_RESIN_TAPPING_KNIFE);
                        entries.add(ModItems.DIAMOND_RESIN_TAPPING_KNIFE);
                        entries.add(ModItems.IRON_HAMMER);
                        entries.add(ModItems.DIAMOND_HAMMER);
                        entries.add(ModItems.IRON_DIAGONAL_PLIERS);
                        entries.add(ModItems.DIAMOND_DIAGONAL_PLIERS);
                        entries.add(ModItems.SOLDER_WIRE);
                    }).build());

    public static final ItemGroup FUNCTIONAL = Registry.register(Registries.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, "functional"),
            ItemGroup.create(ItemGroup.Row.TOP, 10)
                    .displayName(Text.translatable("itemGroup.minternet.functional"))
                    .icon(() -> new ItemStack(ModBlocks.SHAPING_TABLE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SHAPING_TABLE);
                        entries.add(ModBlocks.WELDING_TABLE);
                    }).build());

    public static final ItemGroup MATERIAL = Registry.register(Registries.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, "material"),
            ItemGroup.create(ItemGroup.Row.TOP, 11)
                    .displayName(Text.translatable("itemGroup.minternet.material"))
                    .icon(() -> new ItemStack(ModItems.DIAMOND_PLATE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ROSIN);
                        entries.add(ModItems.COPPER_PLATE);
                        entries.add(ModItems.DIAMOND_PLATE);
                        entries.add(ModItems.IRON_PLATE);
                        entries.add(ModItems.TIN_PLATE);
                        entries.add(ModItems.CRUDE_COPPER_ROD);
                        entries.add(ModItems.CRUDE_TIN_ROD);
                        entries.add(ModItems.CRUDE_IRON_ROD);
                        entries.add(ModItems.COPPER_WIRE);
                        entries.add(ModItems.TIN_WIRE);
                        entries.add(ModItems.IRON_WIRE);
                    }).build());
    public static void registerModItemGroups() {
        Minternet.LOGGER.info("Registering Item Groups");
    }
}
