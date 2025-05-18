package com.samoyed24.minternet.block.entity;

import com.samoyed24.minternet.data.ShapingTableData;
import com.samoyed24.minternet.screen.ShapingTableScreenHandler;
import com.samoyed24.minternet.screen.WeldingTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShapingTableEntity extends BlockEntity implements ExtendedScreenHandlerFactory<ShapingTableData>, ImplementedInventory {
    public static final int INPUT_SLOT0 = 0;
    public static final int INPUT_SLOT1 = 1;
    public static final int TOOL_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;
    protected final PropertyDelegate propertyDelegate;
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
        // TODO
    }
}