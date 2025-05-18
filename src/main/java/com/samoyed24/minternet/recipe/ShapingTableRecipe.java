package com.samoyed24.minternet.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class ShapingTableRecipe implements Recipe<ShapingTableRecipeInput> {
    private final ItemStack output;
//    private final List<Ingredient> recipeItems;
    final Ingredient material1;
    final Ingredient material2;
    final Ingredient tool;

    public ShapingTableRecipe(Ingredient material1, Ingredient material2, Ingredient tool, ItemStack output) {
        this.output = output;
        this.material1 = material1;
        this.material2 = material2;
        this.tool = tool;
    }

    @Override
    public boolean matches(ShapingTableRecipeInput input, World world) {
        if (world.isClient) return false;
        return (((material1.test(input.material1()) && material2.test(input.material2()))
        || (material2.test(input.material1()) && material1.test(input.material2())))
        && tool.test(input.tool()));
    }

    @Override
    public ItemStack craft(ShapingTableRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 3 && height >= 1;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<ShapingTableRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "shaping_table";
    }

    public static class Serializer implements RecipeSerializer<ShapingTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "shaping_table";
        private static final MapCodec<ShapingTableRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
            return instance.group(Ingredient.ALLOW_EMPTY_CODEC.fieldOf("material1").forGetter((recipe) -> {
                return recipe.material1;
            }), Ingredient.ALLOW_EMPTY_CODEC.fieldOf("material2").forGetter((recipe) -> {
                return recipe.material2;
            }), Ingredient.ALLOW_EMPTY_CODEC.fieldOf("tool").forGetter((recipe) -> {
                return recipe.tool;
            }), ItemStack.VALIDATED_CODEC.fieldOf("output").forGetter((recipe) -> {
                return recipe.output;
            })).apply(instance, ShapingTableRecipe::new);
        });
        public static final PacketCodec<RegistryByteBuf, ShapingTableRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);
//        public static final MapCodec<ShapingTableRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
//                (Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients")).flatXmap(ingredients -> {
//                    Ingredient[] ingredients1 = (Ingredient[]) ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
//                    if (ingredients1.length == 0) {
//                        return DataResult.error(() -> "No ingredients");
//                    }
//                    if (ingredients1.length > 3) {
//                        return DataResult.error(() -> "Too many ingredients");
//                    }
//                    return  DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredients1));
//                }, DataResult::success).forGetter()
//        ))
        @Override
        public MapCodec<ShapingTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ShapingTableRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static ShapingTableRecipe read(RegistryByteBuf buf) {
            Ingredient material1 = (Ingredient)Ingredient.PACKET_CODEC.decode(buf);
            Ingredient material2 = (Ingredient)Ingredient.PACKET_CODEC.decode(buf);
            Ingredient tool = (Ingredient)Ingredient.PACKET_CODEC.decode(buf);
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new ShapingTableRecipe(material1, material2, tool, output);
        }

        private static void write(RegistryByteBuf buf, ShapingTableRecipe recipe) {
            Ingredient.PACKET_CODEC.encode(buf, recipe.material1);
            Ingredient.PACKET_CODEC.encode(buf, recipe.material2);
            Ingredient.PACKET_CODEC.encode(buf, recipe.tool);
            ItemStack.PACKET_CODEC.encode(buf, recipe.getResult(null));
        }
    }
}
