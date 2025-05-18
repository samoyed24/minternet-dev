package com.samoyed24.minternet.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class AbstractWidgetScreen extends HandledScreen {
    public AbstractWidgetScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Environment(EnvType.CLIENT)
    interface ButtonWidget {
        void tick(int level);
    }

    @Environment(EnvType.CLIENT)
    abstract static class BaseButtonWidget extends PressableWidget implements ButtonWidget {
        public Identifier disabled_texture;
        public Identifier selected_texture;
        public Identifier highlighted_texture;
        public Identifier button_texture;
        private boolean disabled;

        protected BaseButtonWidget(int x, int y) {
            super(x, y, 22, 22, ScreenTexts.EMPTY);
        }

        protected BaseButtonWidget(int x, int y, Text message) {
            super(x, y, 22, 22, message);
        }

        public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
            Identifier identifier;
            if (!this.active) {
                identifier = disabled_texture;
            } else if (this.disabled) {
                identifier = selected_texture;
            } else if (this.isSelected()) {
                identifier = highlighted_texture;
            } else {
                identifier = button_texture;
            }

            context.drawGuiTexture(identifier, this.getX(), this.getY(), this.width, this.height);
            this.renderExtra(context);
        }

        protected abstract void renderExtra(DrawContext context);

        public boolean isDisabled() {
            return this.disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public void appendClickableNarrations(NarrationMessageBuilder builder) {
            this.appendDefaultNarrations(builder);
        }
    }
}
