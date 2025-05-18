package com.samoyed24.minternet.block.entity;

import com.samoyed24.minternet.data.ShapingTableData;
import com.samoyed24.minternet.recipe.custom.ShapingTableRecipe;
import com.samoyed24.minternet.recipe.custom.ShapingTableRecipeInput;
import com.samoyed24.minternet.screen.ShapingTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class ShapingTableEntity extends BlockEntity implements ExtendedScreenHandlerFactory<ShapingTableData>, ImplementedInventory {
    public static final int INPUT_SLOT0 = 0;
    public static final int INPUT_SLOT1 = 1;
    public static final int TOOL_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;
    public boolean is_button_click = false;
    protected final PropertyDelegate propertyDelegate;
    public static final int MAX_PROGRESS = 72;
    private int progress = 0;
    private int maxProgress = 72;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    public ShapingTableEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.SHAPING_TABLE_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ShapingTableEntity.this.progress;
                    case 1 -> ShapingTableEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ShapingTableEntity.this.progress = value;
                    case 1 -> ShapingTableEntity.this.maxProgress = value;
                };
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ShapingTableScreenHandler(syncId, playerInventory, this.propertyDelegate, this);
    }


    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        progress = nbt.getInt("shaping_table");
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, registryLookup);
        nbt.putInt("shaping_table", progress);
//        return nbt;
    }

    @Override
    public ShapingTableData getScreenOpeningData(ServerPlayerEntity player) {
        return new ShapingTableData(pos);
    }

    public void onTick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;
        if (hasRecipe()) {
//            Minternet.LOGGER.info("set maxProgress");
            propertyDelegate.set(1, MAX_PROGRESS);
        } else {
            propertyDelegate.set(1, 0);
        }
        if (is_button_click && isOutputSlotAvailable()) {
            if (hasRecipe()) {
                this.progress++;
                markDirty(world, pos, state);

                if (this.progress >= this.maxProgress) {
                    craftItem();
                    this.progress = 0;
                    is_button_click = false;
                }
            } else {
                this.progress = 0;
                is_button_click = false;
            }
        } else {
            this.progress = 0;
            is_button_click = false;
            markDirty(world, pos, state);
        }
    }

    private void craftItem() {
        Optional<RecipeEntry<ShapingTableRecipe>> recipe = getCurrentRecipe();
        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
        this.removeStack(INPUT_SLOT0, 1);
        this.removeStack(INPUT_SLOT1, 1);
        this.getStack(TOOL_SLOT).damage(1, (ServerWorld) getWorld(), null, (item) -> {});

    }
    private Optional<RecipeEntry<ShapingTableRecipe>> getCurrentRecipe() {
        SimpleInventory inventory1 = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inventory1.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(ShapingTableRecipe.Type.INSTANCE, new ShapingTableRecipeInput(getStack(INPUT_SLOT0), getStack(INPUT_SLOT1), getStack(TOOL_SLOT)), getWorld());
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
//        ItemStack result = new ItemStack(ModItems.ICE_ETHER);
//        boolean hasInput = getStack(INPUT_SLOT).getItem() == Items.ICE;
//        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertIntoOutputSlot(result.getItem());
        Optional<RecipeEntry<ShapingTableRecipe>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null)) &&
                canInsertIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private boolean canInsertIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getItem() == item;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= this.getMaxCountPerStack();
    }

    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() <= this.getMaxCountPerStack();
    }
}