package com.samoyed24.minternet.datagen;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public static final List<ItemConvertible> TIN = List.of(
            ModItems.RAW_TIN,
            ModBlocks.TIN_ORE
    );
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.TIN_INGOT,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK);
//        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.MISC, ModItems.TIN_NUGGET,
//                RecipeCategory.MISC, ModItems.TIN_INGOT);
        offerSmelting(recipeExporter, TIN, RecipeCategory.MISC, ModItems.TIN_INGOT,
                0.7f, 200, "tin");
        offerBlasting(recipeExporter, TIN, RecipeCategory.MISC, ModItems.TIN_INGOT,
                0.7f, 100, "tin");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETWORK_CARD, 1)
                .pattern("RRR")
                .pattern("TGT")
                .pattern("PPP")
                .input('R', Items.REDSTONE)
                .input('T', ModItems.TIN_INGOT)
                .input('G', Items.GOLD_INGOT)
                .input('P', Items.PAPER)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.TIN_INGOT))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "network_card"));
    }
}
