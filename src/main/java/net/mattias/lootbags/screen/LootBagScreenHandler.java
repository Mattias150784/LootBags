package net.mattias.lootbags.screen;


import net.mattias.lootbags.inventory.LootBagContainer;
import net.mattias.lootbags.item.custom.CommonLootBagItem;
import net.mattias.lootbags.registry.ScreenhandlerTypeRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LootBagScreenHandler extends AbstractContainerMenu {
    private final LootBagContainer container;

    public LootBagScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new LootBagContainer(NonNullList.withSize(CommonLootBagItem.DEFAULT_MAX_STACK_SIZE, ItemStack.EMPTY)));
    }

    public LootBagScreenHandler(int syncId, Inventory playerInventory, LootBagContainer container) {
        super(ScreenhandlerTypeRegistry.LOOTBAGS_SCREENHANDLER.get(), syncId);
        checkContainerSize(container, CommonLootBagItem.DEFAULT_MAX_STACK_SIZE);
        this.container = container;
        container.startOpen(playerInventory.player);

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 8; ++k) {
                this.addSlot(new Slot(container, k + j * 8, 17 + k * 18, 12 + j * 18) {

                });
            }
        }

        int playerInventoryYOffset = 1;

        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 85 + j * 18 + playerInventoryYOffset));
            }
        }

        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 143 + playerInventoryYOffset));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int i) {
        if (i >= 0 && i < this.slots.size()) {
            ItemStack itemStack = ItemStack.EMPTY;
            Slot slot = this.slots.get(i);
            if (slot.hasItem()) {
                ItemStack itemStack2 = slot.getItem();
                itemStack = itemStack2.copy();
                if (i < CommonLootBagItem.DEFAULT_MAX_STACK_SIZE) {
                    if (!this.moveItemStackTo(itemStack2, CommonLootBagItem.DEFAULT_MAX_STACK_SIZE, this.slots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemStack2, 0, CommonLootBagItem.DEFAULT_MAX_STACK_SIZE, false)) {
                    return ItemStack.EMPTY;
                }

                if (itemStack2.isEmpty()) {
                    slot.setByPlayer(ItemStack.EMPTY);
                } else {
                    slot.setChanged();
                }

                if (itemStack2.getCount() == itemStack.getCount()) {
                    return ItemStack.EMPTY;
                }

                slot.onTake(player, itemStack2);
            }

            return itemStack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        if (this.container instanceof LootBagContainer) {
            this.container.setChanged();
        }
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
        if (this.container instanceof LootBagContainer) {
            this.container.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

}