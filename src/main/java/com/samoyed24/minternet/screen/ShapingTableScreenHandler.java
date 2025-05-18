package com.samoyed24.minternet.screen;

import com.samoyed24.minternet.block.entity.ShapingTableEntity;
import com.samoyed24.minternet.data.ShapingTableData;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ShapingTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final ShapingTableEntity blockEntity;
    public ShapingTableScreenHandler(int syncId, PlayerInventory playerInventory, ShapingTableData data) {
        this(syncId, playerInventory, new ArrayPropertyDelegate(2), playerInventory.player.getWorld().getBlockEntity(data.pos()));
    }

    // 这个构造器是在服务器的 BlockEntity 中被调用的，无需先调用其他构造器，服务器知道容器的物品栏
    // 并直接将其作为参数传入。然后物品栏在客户端完成同步。
    public ShapingTableScreenHandler(int syncId, PlayerInventory playerInventory, PropertyDelegate propertyDelegate, BlockEntity blockEntity) {
        super(ModScreenHandlers.SHAPING_TABLE_SCREEN_HANDLER, syncId);
        checkSize((Inventory) blockEntity, 4);
        this.inventory = (Inventory) blockEntity;
        // 玩家开启时，一些物品栏有自定义的逻辑。
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = propertyDelegate;
        addProperties(propertyDelegate);
        this.blockEntity = (ShapingTableEntity) blockEntity;

        // 这会将槽位放置在 3×3 网格的正确位置中。这些槽位在客户端和服务器中都存在！
        // 但是这不会渲染槽位的背景，这是 Screens 类的工作
        this.addSlot(new Slot(inventory, 0, 35, 35));
        this.addSlot(new Slot(inventory, 1, 57, 35));
        this.addSlot(new Slot(inventory, 2, 84, 54));
        this.addSlot(new Slot(inventory, 3, 116, 35));

        int m;
        int l;
        // 玩家物品栏
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // 玩家快捷栏
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    // Shift + 玩家物品栏槽位
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }
    public int getScaledProgress() {
        final int progressArrowSize = 100;
        int progress = propertyDelegate.get(0);
        int maxProgress = propertyDelegate.get(1);
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
}
