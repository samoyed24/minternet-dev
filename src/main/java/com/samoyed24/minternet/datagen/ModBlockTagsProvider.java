package com.samoyed24.minternet.datagen;

import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.tags.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.ROUTER)
                .add(ModBlocks.SERVER)
                .add(ModBlocks.TIN_BLOCK)
                .add(ModBlocks.TIN_ORE)
                .add(ModBlocks.SHAPING_TABLE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ROUTER)
                .add(ModBlocks.SERVER)
                .add(ModBlocks.TIN_BLOCK)
                .add(ModBlocks.TIN_ORE)
                .add(ModBlocks.SHAPING_TABLE);

        getOrCreateTagBuilder(ModBlockTags.RUBBER_FROM_LIST)
                .add(Blocks.SPRUCE_LOG);
    }
}
