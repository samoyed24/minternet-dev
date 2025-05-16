package com.samoyed24.minternet.datagen;

import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModZHCNLanguageProvider extends FabricLanguageProvider {
    public ModZHCNLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.NETWORK_CARD, "网络接口卡");
        translationBuilder.add(ModItems.RAW_TIN, "粗锡");
        translationBuilder.add(ModItems.TIN_INGOT, "锡锭");
        translationBuilder.add(ModItems.TIN_NUGGET, "锡粒");
        translationBuilder.add(ModItems.RESIN, "树脂");

        translationBuilder.add(ModBlocks.SERVER, "服务器");
        translationBuilder.add(ModBlocks.ROUTER, "路由器");
        translationBuilder.add(ModBlocks.TIN_BLOCK, "锡块");
        translationBuilder.add(ModBlocks.TIN_ORE, "锡矿石");

        translationBuilder.add("itemGroup.minternet.network", "Minternet: 网络");
        translationBuilder.add("itemGroup.minternet.mineral", "Minternet: 矿物");
        translationBuilder.add("itemGroup.minternet.natural", "Minternet: 自然生成");
    }
}
