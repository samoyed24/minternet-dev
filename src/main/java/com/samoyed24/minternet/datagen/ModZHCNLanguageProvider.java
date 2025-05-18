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
        translationBuilder.add(ModItems.ROSIN, "松香");
        translationBuilder.add(ModItems.DIAMOND_RESIN_TAPPING_KNIFE, "钻石割胶刀");
        translationBuilder.add(ModItems.IRON_RESIN_TAPPING_KNIFE, "铁割胶刀");
        translationBuilder.add(ModItems.DIAMOND_HAMMER, "钻石锤");
        translationBuilder.add(ModItems.IRON_HAMMER, "铁锤");
        translationBuilder.add(ModItems.IRON_DIAGONAL_PLIERS, "铁斜口钳");
        translationBuilder.add(ModItems.DIAMOND_DIAGONAL_PLIERS, "钻石斜口钳");
        translationBuilder.add(ModItems.IRON_PLATE, "铁板 ");
        translationBuilder.add(ModItems.DIAMOND_PLATE, "钻石板");
        translationBuilder.add(ModItems.TIN_PLATE,  "锡板");
        translationBuilder.add(ModItems.COPPER_PLATE, "铜板");
        translationBuilder.add(ModItems.CRUDE_TIN_ROD, "粗锡棒");
        translationBuilder.add(ModItems.CRUDE_IRON_ROD, "粗铁棒");
        translationBuilder.add(ModItems.CRUDE_COPPER_ROD, "粗铜棒");
        translationBuilder.add(ModItems.TIN_WIRE, "锡丝");
        translationBuilder.add(ModItems.IRON_WIRE, "铁丝");;
        translationBuilder.add(ModItems.COPPER_WIRE, "铜丝");
        translationBuilder.add(ModItems.SOLDER_WIRE, "焊锡丝");

        translationBuilder.add(ModBlocks.SERVER, "服务器");
        translationBuilder.add(ModBlocks.ROUTER, "路由器");
        translationBuilder.add(ModBlocks.TIN_BLOCK, "锡块");
        translationBuilder.add(ModBlocks.TIN_ORE, "锡矿石");
        translationBuilder.add(ModBlocks.WELDING_TABLE, "焊接工作台");
        translationBuilder.add(ModBlocks.SHAPING_TABLE, "成型工作台");

        translationBuilder.add("itemGroup.minternet.network", "Minternet: 网络");
        translationBuilder.add("itemGroup.minternet.mineral", "Minternet: 矿物");
        translationBuilder.add("itemGroup.minternet.natural", "Minternet: 自然生成");
        translationBuilder.add("itemGroup.minternet.tool", "Minternet: 工具");
        translationBuilder.add("itemGroup.minternet.functional", "Minternet: 功能性方块");
        translationBuilder.add("itemGroup.minternet.material", "Minternet: 材料");



        translationBuilder.add("item.minternet.resin_tapping_knife.tooltip", "§7可从云杉原木中获取树脂§r");
        translationBuilder.add("item.minternet.resin.tooltip", "§7可以吃但最好别§r");

    }
}
