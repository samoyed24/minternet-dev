package com.samoyed24.minternet.tags;

import com.samoyed24.minternet.Minternet;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    // 能够采集树脂的木头标签
    public static final TagKey<Block> RUBBER_FROM_LIST = of("rubber_from_list");
    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Minternet.MOD_ID, id));
    }
    public static void registerModBlockTags() {
        Minternet.LOGGER.info("Registering ModBlockTags");
    }
}
