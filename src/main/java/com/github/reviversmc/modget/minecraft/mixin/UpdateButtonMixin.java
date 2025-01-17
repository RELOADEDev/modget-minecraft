package com.github.reviversmc.modget.minecraft.mixin;

import com.github.reviversmc.modget.minecraft.Modget;
import com.github.reviversmc.modget.minecraft.client.ModsOverview;
import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.ModMenuTexturedButtonWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModsScreen.class)
public abstract class UpdateButtonMixin extends Screen {
    private static final Identifier UPDATE_BUTTON_LOCATION = new Identifier(Modget.NAMESPACE, "textures/gui/install_button.png");
    private int paneWidth;

    protected UpdateButtonMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void onInit(CallbackInfo ci) {
        int paneY = 48;
        this.addDrawableChild(new ModMenuTexturedButtonWidget(width - 24, paneY - 22, 20, 20, 0, 0, UPDATE_BUTTON_LOCATION, 32, 64,
                button -> MinecraftClient.getInstance().setScreen(new ModsOverview(this)), LiteralText.EMPTY,
                (button, matrices, mouseX, mouseY) -> {
                    if (!button.isHovered()) {
                        return;
                    }
                    this.renderTooltip(matrices, new LiteralText("Updates"), mouseX, mouseY);
                }));
    }
}
