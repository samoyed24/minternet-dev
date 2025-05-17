package com.samoyed24.minternet.datagen;

import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModENUSLanguageProvider extends FabricLanguageProvider {
    public ModENUSLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.NETWORK_CARD, "NIC");
        translationBuilder.add(ModItems.RAW_TIN, "Raw Tin");
        translationBuilder.add(ModItems.TIN_INGOT, "Tin Ingot");
        translationBuilder.add(ModItems.TIN_NUGGET, "Tin Nugget");
        translationBuilder.add(ModItems.RESIN, "Resin");
        translationBuilder.add(ModItems.ROSIN, "Rosin");
        translationBuilder.add(ModItems.DIAMOND_RESIN_TAPPING_KNIFE, "Diamond Resin Tapping Knife");
        translationBuilder.add(ModItems.IRON_RESIN_TAPPING_KNIFE, "Iron Resin Tapping Knife");

        translationBuilder.add(ModBlocks.SERVER, "Server");
        translationBuilder.add(ModBlocks.ROUTER, "Router");
        translationBuilder.add(ModBlocks.TIN_BLOCK, "Tin Block");
        translationBuilder.add(ModBlocks.TIN_ORE, "Tin Ore");

        translationBuilder.add(ModBlocks.WELDING_TABLE, "Welding Table");
        translationBuilder.add(ModBlocks.SHAPING_TABLE, "Shaping Table");


        translationBuilder.add("itemGroup.minternet.network", "Minternet: Network");
        translationBuilder.add("itemGroup.minternet.mineral", "Minternet: Mineral");
        translationBuilder.add("itemGroup.minternet.natural", "Minternet: Natural");
        translationBuilder.add("itemGroup.minternet.tool", "Minternet: Tool");
        translationBuilder.add("itemGroup.minternet.functional", "Minternet: Functional");

        translationBuilder.add("item.minternet.resin_tapping_knife.tooltip", "§7Tap resin from spruce oak§r");
        translationBuilder.add("item.minternet.resin.tooltip", "§7Eatable but we suggest not§r");
    }
}
