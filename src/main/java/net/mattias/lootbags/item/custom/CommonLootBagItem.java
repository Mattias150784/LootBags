package net.mattias.lootbags.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.mattias.lootbags.inventory.LootBagContainer; // Import your container class

public class CommonLootBagItem extends Item {
    public CommonLootBagItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (hand == InteractionHand.OFF_HAND || player.isCrouching()) {
            return InteractionResultHolder.fail(itemstack);
        }

        if (!level.isClientSide) {
            // Ensure player is a server player
            if (player instanceof ServerPlayer serverPlayer) {
                // Create a container for the loot bag GUI
                NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Common Loot Bag");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                        // Create and return the menu for the loot bag GUI
                        return new LootBagContainer(id, playerInventory, new SimpleContainer(5));
                    }
                }, buffer -> buffer.writeItemStack(itemstack, false));
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide);
    }
}
