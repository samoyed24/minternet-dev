package com.samoyed24.minternet.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public class ShapingTableRecipeInput implements RecipeInput {
    private final ItemStack material1;
    private final ItemStack material2;
    private final ItemStack tool;

    public ShapingTableRecipeInput(ItemStack material1, ItemStack material2, ItemStack tool) {
        this.material1 = material1;
        this.material2 = material2;
        this.tool = tool;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        ItemStack itemStack;
        switch (slot) {
            case 0:
                itemStack = this.material1;
                break;
            case 1:
                itemStack = this.material2;
                break;
            case 2:
                itemStack = this.tool;
                break;
            default:
                throw new IllegalArgumentException("Recipe does not contain slot " + slot);
        }

        return itemStack;
    }

    @Override
    public int getSize() {
        return 3;
    }

    public boolean isEmpty() {
        return this.material1.isEmpty() && this.material2.isEmpty() && this.tool.isEmpty();
    }
    public ItemStack material1() {
        return this.material1;
    }

    public ItemStack material2() {
        return this.material2;
    }

    public ItemStack tool() {
        return this.tool;
    }
}
