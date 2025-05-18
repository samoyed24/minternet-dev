package com.samoyed24.minternet.datagen;

import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WELDING_TABLE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);

        // 对于已经通过外部添加模型JSON的方块，在这个部分单独注册BlockState
//        blockStateModelGenerator.registerSimpleState(ModBlocks.WELDING_TABLE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SHAPING_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.NETWORK_CARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RESIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_RESIN_TAPPING_KNIFE, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_RESIN_TAPPING_KNIFE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROSIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_DIAGONAL_PLIERS, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_DIAGONAL_PLIERS, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_PLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_PLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_PLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_TIN_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_IRON_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_WIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_WIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_PLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_WIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRUDE_COPPER_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOLDER_WIRE, Models.GENERATED);
    }
}
