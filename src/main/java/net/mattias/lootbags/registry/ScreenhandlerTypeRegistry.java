package net.mattias.lootbags.registry;

import net.mattias.lootbags.LootBags;
import net.mattias.lootbags.screen.LootBagScreenHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ScreenhandlerTypeRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, LootBags.MOD_ID);

    public static final RegistryObject<MenuType<LootBagScreenHandler>> LOOTBAGS_SCREENHANDLER = register("lootbags_screenhandler", () -> new MenuType<>(LootBagScreenHandler::new, FeatureFlags.VANILLA_SET));

    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, Supplier<MenuType<T>> menuType) {
        return MENU_TYPES.register(name, menuType);


    }
}
