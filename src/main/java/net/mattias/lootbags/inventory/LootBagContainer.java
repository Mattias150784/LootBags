package net.mattias.lootbags.inventory;

import net.mattias.lootbags.item.custom.CommonLootBagItem;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;


public class LootBagContainer implements Container, StackedContentsCompatible {
    private NonNullList<ItemStack> stacks = NonNullList.withSize(CommonLootBagItem.DEFAULT_MAX_STACK_SIZE, ItemStack.EMPTY);

    @Nullable
    private List<ContainerListener> listeners;

    private @Nullable Player player;

    public LootBagContainer(NonNullList<ItemStack> itemStacks) {
        this.stacks = itemStacks;
    }

    public LootBagContainer(NonNullList<ItemStack> itemStacks, @Nullable Player player) {
        this(itemStacks);
        this.player = player;
    }

    @Override
    public int getContainerSize() {
        return CommonLootBagItem.DEFAULT_MAX_STACK_SIZE;
    }

    @Override
    public boolean isEmpty() { return this.stacks.stream().allMatch(Predicate.isEqual(ItemStack.EMPTY)); }

    @Override
    public @NotNull ItemStack getItem(int i) {
        return stacks.get(i);
    }

    @Override
    public @NotNull ItemStack removeItem(int i, int j) {
        return ContainerHelper.removeItem(stacks, i, j);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int i) {
        return ContainerHelper.takeItem(this.stacks, i);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        stacks.set(i, itemStack);
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return !player.isDeadOrDying();
    }

    @Override
    public void clearContent() {
        this.stacks.clear();
        this.setChanged();
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {
        for (ItemStack itemStack : this.stacks) {
            stackedContents.accountStack(itemStack);
        }
        this.setChanged();
    }

    @Override @SuppressWarnings("all")
    public void setChanged() {

        System.out.println("setChanged called");

        if (this.player == null) {

            System.out.println("player was null");
            return;
        }
      }
    }
