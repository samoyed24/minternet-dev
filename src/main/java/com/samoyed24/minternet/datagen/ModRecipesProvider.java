package com.samoyed24.minternet.datagen;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
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
        offerReversibleCompactingRecipesWithCompactingRecipeGroup(
                recipeExporter, RecipeCategory.MISC, ModItems.TIN_NUGGET,
                RecipeCategory.MISC, ModItems.TIN_INGOT, "tin_nugget_to_ingot",
                "minternet:tin_ingot"
        );
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Items.TORCH, 2)
                .pattern("R")
                .pattern("S")
                .input('R', ModItems.RESIN)
                .input('S', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.RESIN))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "torch_from_resin"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHAPING_TABLE , 1)
                .pattern("III")
                .pattern("TST")
                .pattern("WWW")
                .input('T', ModItems.TIN_INGOT)
                .input('S', Blocks.SMITHING_TABLE)
                .input('I', Items.IRON_INGOT)
                .input('W', ItemTags.PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.TIN_INGOT))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "shaping_table"));
    }
}
