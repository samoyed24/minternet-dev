package com.samoyed24.minternet.datagen;

import com.samoyed24.minternet.item.ModItems;
import com.samoyed24.minternet.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModItemTags.CAN_BE_SHAPED_BY_IRON_TOOL)
                .add(Items.COPPER_INGOT)
                .add(Items.GOLD_INGOT)
                .add(Items.IRON_INGOT)
                .add(ModItems.TIN_INGOT)
                .add(ModItems.COPPER_PLATE)
                .add(ModItems.TIN_PLATE)
                .add(ModItems.IRON_PLATE);
//                .add(ModItems.GOLD_PLATE)
        getOrCreateTagBuilder(ModItemTags.CAN_BE_SHAPED_BY_DIAMOND_TOOL)
                .forceAddTag(ModItemTags.CAN_BE_SHAPED_BY_IRON_TOOL)
                .add(Items.DIAMOND)
                .add(ModItems.DIAMOND_PLATE);
    }
}
