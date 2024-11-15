package me.cg360.mod.bridging.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import me.cg360.mod.bridging.BridgingMod;
import me.cg360.mod.bridging.compat.BridgingCrosshairTweaks;
import me.cg360.mod.bridging.raytrace.PlacementAlignment;
import me.cg360.mod.bridging.raytrace.BridgingStateTracker;
import me.cg360.mod.bridging.util.GameSupport;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class CrosshairRenderingMixin {

    @Unique
    private static final int ICON_SIZE = 32;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Final private DebugScreenOverlay debugOverlay;

    @Inject(method = "renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At(value = "TAIL"))
    public void renderPlacementAssistMarker(GuiGraphics gui, DeltaTracker deltaTracker, CallbackInfo ci) {
        if(BridgingStateTracker.getLastTickTarget() == null) return;
        if(BridgingCrosshairTweaks.forceHidden) return;
        if(this.minecraft.options.hideGui) return;

        if(!BridgingMod.getConfig().shouldShowCrosshair()) return;

        boolean isBridgingActive = BridgingMod.getConfig().isBridgingEnabled() &&
                                   (!BridgingMod.getConfig().shouldOnlyBridgeWhenCrouched() || GameSupport.isControllerCrouching());

        if(!isBridgingActive)
            return;

        Direction direction = BridgingStateTracker.getLastTickTarget().getB();
        PlacementAlignment alignment = PlacementAlignment.from(direction);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(CoreShaders.RENDERTYPE_LINES);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR,
                GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
                GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO
        );

        int w = gui.guiWidth();
        int h = gui.guiHeight();

        if(alignment == null) return;

        int x = ((w - ICON_SIZE + 1) / 2);
        int y = ((h - ICON_SIZE + 1) / 2);

        y += BridgingCrosshairTweaks.yShift;
        y += this.debugOverlay.showDebugScreen() ? 15 : 0;

        gui.blitSprite(
                RenderType::crosshair,
                alignment.getTexturePath(),
                x, y,
                ICON_SIZE, ICON_SIZE
        );

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(CoreShaders.RENDERTYPE_LINES);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    }

}
