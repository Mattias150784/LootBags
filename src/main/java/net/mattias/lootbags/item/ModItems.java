package net.mattias.lootbags.item;

import net.mattias.lootbags.LootBags;
import net.mattias.lootbags.LootBags;
import net.mattias.lootbags.item.custom.CommonLootBagItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LootBags.MOD_ID);

    public static final RegistryObject<Item> COMMON_LOOT_BAG = ITEMS.register("common_loot_bag",
            () -> new CommonLootBagItem(new Item.Properties()));

    public static final RegistryObject<Item> UNCOMMON_LOOT_BAG = ITEMS.register("uncommon_loot_bag",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RARE_LOOT_BAG = ITEMS.register("rare_loot_bag",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
