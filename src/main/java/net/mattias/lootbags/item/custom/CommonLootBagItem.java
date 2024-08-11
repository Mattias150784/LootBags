package net.mattias.lootbags.item.custom;

import net.mattias.lootbags.inventory.LootBagContainer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.mattias.lootbags.screen.LootBagScreenHandler;

public class CommonLootBagItem extends Item {
    public CommonLootBagItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            NetworkHooks.openScreen((ServerPlayer) player, new LootBagContainer(), buf -> buf.writeItem(player.getItemInHand(hand)));
        }

        return new InteractionResultHolder<>(InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
