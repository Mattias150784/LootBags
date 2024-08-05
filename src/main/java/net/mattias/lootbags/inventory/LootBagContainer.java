package net.mattias.lootbags.inventory;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;



public class LootBagContainer implements MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler(9); // Adjust size as needed
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
    public Component getName() {
        return Component.translatable("screen.lootbag.item");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("screen.lootbag.item");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new LootBagMenu(id, playerInventory, this);
    }

    public static void openGUI(ServerPlayer serverPlayerEntity, ItemStack stack, byte screenID)
    {
        if(!serverPlayerEntity.level().isClientSide)
        {
            if(screenID == Reference.ITEM_SCREEN_ID)
            {
                serverPlayerEntity.openMenu(new LootBagContainer(stack, serverPlayerEntity, screenID), packetBuffer -> packetBuffer.writeByte(screenID));
            }

            if(screenID == Reference.WEARABLE_SCREEN_ID)
            {
                serverPlayerEntity.openMenu(CapabilityUtils.getlootbagItem(serverPlayerEntity), packetBuffer -> packetBuffer.writeByte(screenID));
            }
        }
    }
}
