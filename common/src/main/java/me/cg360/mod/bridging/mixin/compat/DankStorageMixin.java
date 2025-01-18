package me.cg360.mod.bridging.mixin.compat;

import com.mojang.logging.LogUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.dankstorage.item.DankItem;

@Mixin(DankItem.class)
public class DankStorageMixin {

    @Inject(method = "useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;", at = @At("HEAD"))
    private void useOn(UseOnContext ctx, CallbackInfoReturnable<InteractionResult> cir) {
        LogUtils.getLogger().info("Dank storage useOn");
    }


}
