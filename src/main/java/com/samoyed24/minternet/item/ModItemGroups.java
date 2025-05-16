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
            ItemGroup.create(ItemGroup.Row.TOP, 7)
                    .displayName(Text.translatable("itemGroup.minternet.network"))
                    .icon(() -> new ItemStack(ModItems.NETWORK_CARD))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.NETWORK_CARD);
                        entries.add(ModBlocks.SERVER);
                        entries.add(ModBlocks.ROUTER);
                    })
                    .build());
    public static final ItemGroup MINERAL = Registry.register(Registries.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, "mineral"),
            ItemGroup.create(ItemGroup.Row.TOP, 8)
                    .displayName(Text.translatable("itemGroup.minternet.mineral"))
                    .icon(() -> new ItemStack(ModItems.TIN_INGOT))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TIN_INGOT);
                        entries.add(ModBlocks.TIN_BLOCK);
                        entries.add(ModItems.RAW_TIN);
                        entries.add(ModItems.TIN_NUGGET);
                        entries.add(ModBlocks.TIN_ORE);
                    }).build());
    public static void registerModItemGroups() {
        Minternet.LOGGER.info("Registering Item Groups");
    }
}
