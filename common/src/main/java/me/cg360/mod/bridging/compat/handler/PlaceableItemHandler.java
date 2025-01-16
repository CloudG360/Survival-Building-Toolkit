package me.cg360.mod.bridging.compat.handler;

import me.cg360.mod.bridging.compat.SpecialBridgingHandler;
import net.minecraft.world.item.ItemStack;

public class PlaceableItemHandler implements SpecialBridgingHandler {

    public static final PlaceableItemHandler INSTANCE = new PlaceableItemHandler();

    @Override
    public boolean canBePlaced(ItemStack stack) {
        return true;
    }

}
