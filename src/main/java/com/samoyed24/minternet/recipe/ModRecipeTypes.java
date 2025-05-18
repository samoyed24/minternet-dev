package com.samoyed24.minternet.recipe;

import com.samoyed24.minternet.Minternet;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {
    public static void registerRecipeTypes() {
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Minternet.MOD_ID, ShapingTableRecipe.Serializer.ID),
                ShapingTableRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(Minternet.MOD_ID, ShapingTableRecipe.Type.ID),
                ShapingTableRecipe.Type.INSTANCE);
    }
}