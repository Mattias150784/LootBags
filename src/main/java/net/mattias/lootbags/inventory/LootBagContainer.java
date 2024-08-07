package net.mattias.lootbags.inventory;

import io.netty.buffer.Unpooled;
import net.mattias.lootbags.inventory.menu.LootBagMenu;
import net.mattias.lootbags.inventory.menu.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class LootBagContainer implements MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(5); // Set to 5 slots as per your requirement
    private final Player player;
    private final ItemStack stack;
    private final byte screenID;

    public LootBagContainer(ItemStack stack, Player player, byte screenID) {
        this.player = player;
        this.stack = stack;
        this.screenID = screenID;
    }

    public ItemStackHandler getHandler() {
        return this.inventory;
    }

    public ItemStack getItemStack() {
        return this.stack;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
        buffer.writeItem(this.stack);
        buffer.writeByte(this.screenID);
        return new LootBagMenu(id, playerInventory, buffer);
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("screen.common_lootbag.item");
    }
}
