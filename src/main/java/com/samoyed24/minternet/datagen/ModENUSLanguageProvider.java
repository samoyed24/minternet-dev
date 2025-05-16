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
        translationBuilder.add(ModItems.RAW_TIN, "Raw tin");
        translationBuilder.add(ModItems.TIN_INGOT, "Tin ingot");
        translationBuilder.add(ModItems.TIN_NUGGET, "Tin nugget");

        translationBuilder.add(ModBlocks.SERVER, "Server");
        translationBuilder.add(ModBlocks.ROUTER, "Router");
        translationBuilder.add(ModBlocks.TIN_BLOCK, "Tin block");
        translationBuilder.add(ModBlocks.TIN_ORE, "Tin ore");

        translationBuilder.add(ModBlocks.WELDING_TABLE, "Welding table");

        translationBuilder.add("itemGroup.minternet.network", "Network");
        translationBuilder.add("itemGroup.minternet.mineral", "Mineral");
    }
}
