package com.samoyed24.minternet.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.samoyed24.minternet.Minternet;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ShapingTableScreen extends HandledScreen<ShapingTableScreenHandler> {
//    protected int backgroundWidth = 226;
//    protected int backgroundHeight = 166;
    private static final Identifier TEXTURE = Identifier.of(Minternet.MOD_ID, "textures/gui/container/shaping_table.png");
    public ShapingTableScreen(ShapingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x+80, y+35, 176, 0, 8, handler.getScaledProgress());
        }
    }

//    @Override
//    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//        renderBackground(context, mouseX, mouseY, delta);
//        super.render(context, mouseX, mouseY, delta);
//        drawMouseoverTooltip(context, mouseX, mouseY);
//    }

//    public boolean mouseClicked(double mouseX, double mouseY, int button) {
//        int i = (this.width - this.backgroundWidth) / 2;
//        int j = (this.height - this.backgroundHeight) / 2;
//
//        for(int k = 0; k < 3; ++k) {
//            double d = mouseX - (double)(i + 60);
//            double e = mouseY - (double)(j + 14 + 19 * k);
////            if (d >= 0.0 && e >= 0.0 && d < 108.0 && e < 19.0 && ((EnchantmentScreenHandler)this.handler).onButtonClick(this.client.player, k)) {
////                this.client.interactionManager.clickButton(((EnchantmentScreenHandler)this.handler).syncId, k);
////                return true;
////            }
//        }
//
//        return super.mouseClicked(mouseX, mouseY, button);
//    }

    @Override
    protected void init() {
        super.init();
        // 将标题居中
        titleX = 8;
    }
}
