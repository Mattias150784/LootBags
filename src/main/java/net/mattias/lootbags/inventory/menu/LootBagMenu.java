package net.mattias.lootbags.inventory.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.mattias.lootbags.inventory.LootBagContainer;
import net.mattias.lootbags.item.custom.CommonLootBagItem;

public class LootBagMenu extends AbstractContainerMenu {
    private final LootBagContainer container;

    public LootBagMenu(int id, Inventory playerInventory, FriendlyByteBuf data) {
        super(ModMenuTypes.LOOT_BAG_SLOTS.get(), id); // Name of my Menu
        ItemStack stack = data.readItem();
        Player player = playerInventory.player;
        byte screenID = data.readByte();
        this.container = new LootBagContainer(stack, player, screenID);
        addPlayerSlots(new InvWrapper(playerInventory));
        addLootBagSlots();
    }

    private void addLootBagSlots() {
        // Assuming LootBagContainer.getHandler() provides a compatible IItemHandler
        for (int i = 0; i < container.getHandler().getSlots(); i++) {
            addSlot(new SlotItemHandler(container.getHandler(), i, xPosition(i), yPosition(i)));
        }
    }

    private int xPosition(int index) {
        // Dummy method: calculate X position based on index
        return 8 + index * 18;
    }

    private int yPosition(int index) {
        // Dummy method: calculate Y position based on index
        return 18;
    }

    private void addPlayerSlots(InvWrapper playerInventory) {
        // Add player inventory slots: 9 hotbar + 27 inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new SlotItemHandler(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlot(new SlotItemHandler(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // Implement shift-clicking handling
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
