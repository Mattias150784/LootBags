package net.mattias.lootbags.screen;

import net.mattias.lootbags.LootBags;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LootBagScreen extends AbstractContainerScreen<LootBagScreenHandler> {
    private static final ResourceLocation lootbagTexture = ResourceLocation.fromNamespaceAndPath(LootBags.MOD_ID, "textures/gui/loot_bag_slots.png");


    public LootBagScreen(LootBagScreenHandler screenHandler, Inventory inventory, Component component) {
        super(screenHandler, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        this.titleLabelY = -8;
        this.inventoryLabelY = this.imageHeight - 90;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
        this.renderTooltip(guiGraphics, i, j);
    }

    private void renderBackground(GuiGraphics guiGraphics) {

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2 - 20;
        guiGraphics.blit(lootbagTexture, k, l, 0, -5, this.imageWidth, this.imageHeight + 25);
    }
}