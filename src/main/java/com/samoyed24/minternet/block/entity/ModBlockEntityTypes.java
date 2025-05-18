package com.samoyed24.minternet.block.entity;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.screen.ShapingTableScreenHandler;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Minternet.MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<WeldingTableBlockEntity> WELDING_TABLE_ENTITY = register(
        "welding_table",

        // 对于 1.21.2 之前的版本，请使用 BlockEntityType.Builder。
        BlockEntityType.Builder.create(WeldingTableBlockEntity::new, ModBlocks.WELDING_TABLE).build()
    );
    public static final BlockEntityType<ShapingTableEntity> SHAPING_TABLE_ENTITY = register(
        "shaping_table",
        BlockEntityType.Builder.create(ShapingTableEntity::new, ModBlocks.SHAPING_TABLE).build()
    );

    public static void registerBlockEntityTypes() {

    }
}
