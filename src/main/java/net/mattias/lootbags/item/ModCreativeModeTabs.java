package net.mattias.lootbags.item;

import net.mattias.lootbags.item.ModItems;
import net.mattias.lootbags.LootBags;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LootBags.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LOOT_BAGS = CREATIVE_MODE_TABS.register("loot_bags",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COMMON_LOOT_BAG.get()))
                    .title(Component.translatable("creativetab.loot_bags"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.COMMON_LOOT_BAG.get());
                        pOutput.accept(ModItems.UNCOMMON_LOOT_BAG.get());
                        pOutput.accept(ModItems.RARE_LOOT_BAG.get());


                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}