package com.samoyed24.minternet.block;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.block.custom.WeldingTableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block SERVER = register("server", new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f)));
    public static final Block ROUTER = register("router", new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f)));
    public static final Block TIN_BLOCK = register("tin_block", new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f)));
    public static final Block TIN_ORE = register("tin_ore", new Block(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f)));
    public static final Block WELDING_TABLE = register("welding_table", new WeldingTableBlock(AbstractBlock.Settings.create().requiresTool().strength(3.0f, 3.0f)));
    public static void registerBlockItems(String id, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Minternet.MOD_ID, id), new BlockItem(block, new Item.Settings()));
    }
    public static Block register(String id, Block block) {
        registerBlockItems(id, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Minternet.MOD_ID, id), block);
    }
    public static void registerModBlocks() {
        Minternet.LOGGER.info("Registering ModBlocks");
    }
}
