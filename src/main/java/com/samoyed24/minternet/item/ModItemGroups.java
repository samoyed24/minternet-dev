package com.samoyed24.minternet.item;

import com.samoyed24.minternet.Minternet;
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
    public static final RegistryKey<ItemGroup> NETWORK  = register("network_misc");
    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Minternet.MOD_ID, id));
    }
    public static void registerModItemGroups() {
        Registry.register(Registries.ITEM_GROUP, NETWORK,
                ItemGroup.create(ItemGroup.Row.TOP, 7)
                        .displayName(Text.translatable("itemGroup.minternet.network"))
                        .icon(() -> new ItemStack(ModItems.NETWORK_CARD))
                        .entries((displayContext, entries) -> {
                            entries.add(ModItems.NETWORK_CARD);
                        }).build());
        Minternet.LOGGER.info("Registering Item Groups");
    }
}
