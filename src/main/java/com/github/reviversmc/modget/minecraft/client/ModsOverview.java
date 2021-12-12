package com.github.reviversmc.modget.minecraft.client;

import com.github.reviversmc.modget.minecraft.Modget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class ModsOverview extends Screen {
    private final Screen previousScreen;
    public ModsOverview(Screen previousScreen) {
        super(new LiteralText("Mods"));
        this.previousScreen = previousScreen;
    }
    @Override
    protected void init() {
        assert this.client != null;
        this.client.keyboard.setRepeatEvents(true);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) -> {
                this.client.setScreen(this.previousScreen);
        }));
    }
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 8, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
    @Override
    public void onClose() {
        assert this.client != null;
        this.client.setScreen(this.previousScreen);
    }
}
