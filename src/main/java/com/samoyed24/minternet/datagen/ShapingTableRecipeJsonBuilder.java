//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.samoyed24.minternet.datagen;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.samoyed24.minternet.recipe.ShapingTableRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements.CriterionMerger;
import net.minecraft.advancement.AdvancementRewards.Builder;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ShapingTableRecipeJsonBuilder {
    private final RecipeCategory category;
    private final Ingredient material1;
    private final Ingredient material2;
    private final ItemStack output;
    private final Ingredient tool;
    private final Map<String, AdvancementCriterion<?>> criteria = new LinkedHashMap();

    public ShapingTableRecipeJsonBuilder(RecipeCategory category, Ingredient material1, Ingredient material2, Ingredient tool, ItemStack output) {
        this.category = category;
        this.material1 = material1;
        this.material2 = material2;
        this.tool = tool;
        this.output = output;
    }

    public static ShapingTableRecipeJsonBuilder create(Ingredient material1, Ingredient material2, Ingredient tool, ItemStack output, RecipeCategory category) {
        return new ShapingTableRecipeJsonBuilder(category, material1, material2, tool, output);
    }


    public ShapingTableRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder().criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(Builder.recipe(recipeId)).criteriaMerger(CriterionMerger.OR);
        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::criterion);
        ShapingTableRecipe shapingTableRecipe = new ShapingTableRecipe(this.material1, this.material2, this.tool, this.output);
        exporter.accept(recipeId, shapingTableRecipe, builder.build(recipeId.withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }

    private void validate(Identifier recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(recipeId));
        }
    }
}
