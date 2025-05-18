package com.samoyed24.minternet.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.samoyed24.minternet.Minternet;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
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
        if (this.handler.canClickButton()) {
            context.drawTexture(TEXTURE, x+130, y+62, 176, 15, 32, 17);
            context.drawTextWrapped(this.textRenderer, StringVisitable.plain("work"), x+130+5, y+62+5, 86 - this.textRenderer.getWidth("test"), Colors.GRAY);
        }else {
            context.drawTexture(TEXTURE, x+130, y+62, 176, 32, 32, 17);
        }
        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x+80, y+35, 176, 0, handler.getScaledProgress(), 14);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        double x = mouseX - (this.width - this.backgroundWidth) / 2;
        double y = mouseY - (this.height - this.backgroundHeight) / 2;
        Minternet.LOGGER.info("mouse click");
        if ((this.handler).canClickButton() && x >= 130 && y >= 62 && x < 162 && y < 78 && (this.handler).onButtonClick(this.client.player, 0)) {
            Minternet.LOGGER.info("click button");
            this.client.interactionManager.clickButton((this.handler).syncId, 0);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }


    @Override
    protected void init() {
        super.init();
        // 将标题居中
        titleX = 8;
    }
}
