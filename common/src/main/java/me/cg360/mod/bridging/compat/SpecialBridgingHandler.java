package me.cg360.mod.bridging.compat;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;

public interface SpecialBridgingHandler {

    boolean canBePlaced(ItemStack stack);

    /**
     * @returns The result of the placement interaction.
     *          If null, BridgingMod assumes it needs to place the block itself.
     *          If any other value, BridgingMod skips placing the block and assumes
     *          this method has handled placing it.
     */
    default InteractionResult place(/* todo: context */) {
        return null; // default: pass placing to bridging mod.
    }

}
