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
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.*;
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
    public static final List<ItemConvertible> RESIN = List.of(
            ModItems.RESIN
    );
    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void offerSmoking(
            RecipeExporter exporter, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group
    ) {
        offerMultipleOptions(exporter, RecipeSerializer.SMOKING, SmokingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_smoking");
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
        offerSmoking(recipeExporter, RESIN, RecipeCategory.MISC, ModItems.ROSIN,
                    0.7F, 160, "resin");
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.IRON_HAMMER, 1)
                .pattern("III")
                .pattern(" SI")
                .pattern(" S ")
                .input('I', Items.IRON_INGOT)
                .input('S', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "iron_hammer"));

        // FIXME 目前使用非满耐久的铁锤也能合成钻石锤，后续需要修复！
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_HAMMER, 1)
                .input(ModItems.IRON_HAMMER)
                .input(Items.DIAMOND)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.IRON_HAMMER))
                .criterion("has_diamond", RecipeProvider.conditionsFromItem(Items.DIAMOND))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "diamond_hammer"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.IRON_DIAGONAL_PLIERS, 1)
                .pattern("I I")
                .pattern(" I ")
                .pattern("S S")
                .input('I', ModItems.IRON_PLATE)
                .input('S', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.IRON_PLATE))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "iron_diagonal_pliers"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_DIAGONAL_PLIERS, 1)
                .pattern("D D")
                .pattern(" D ")
                .pattern("S S")
                .input('D', ModItems.DIAMOND_PLATE)
                .input('S', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.DIAMOND_PLATE))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "diamond_diagonal_pliers"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.IRON_RESIN_TAPPING_KNIFE, 1)
                .pattern("I  ")
                .pattern("PI ")
                .pattern("  S")
                .input('I', Items.IRON_INGOT)
                .input('P', ModItems.IRON_PLATE)
                .input('S', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.IRON_PLATE))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "iron_resin_tapping_knife"));
        // FIXME 跟锤子一样的BUG
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_RESIN_TAPPING_KNIFE, 1)
                .input(ModItems.IRON_RESIN_TAPPING_KNIFE)
                .input(ModItems.DIAMOND_PLATE)
                .criterion("has_iron_one", RecipeProvider.conditionsFromItem(ModItems.IRON_RESIN_TAPPING_KNIFE))
                .criterion("has_diamond_plate", RecipeProvider.conditionsFromItem(ModItems.DIAMOND_PLATE))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "diamond_resin_tapping_knife"));
        ShapingTableRecipeJsonBuilder.create(Ingredient.ofStacks(new ItemStack(Items.COAL_BLOCK)), Ingredient.ofStacks(new ItemStack(Items.COAL_BLOCK)), Ingredient.ofStacks(new ItemStack(ModItems.DIAMOND_HAMMER)), new ItemStack(Items.DIAMOND), RecipeCategory.TOOLS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.COAL_BLOCK))
                .offerTo(recipeExporter, Identifier.of(Minternet.MOD_ID, "coal_block_to_diamond_shaping"));
    }
}
