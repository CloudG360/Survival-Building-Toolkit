package me.cg360.mod.bridging.compat.handler;

import me.cg360.mod.bridging.compat.SpecialBridgingHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PlaceableItemHandler implements SpecialBridgingHandler {

    public static final PlaceableItemHandler INSTANCE = new PlaceableItemHandler();

    @Override
    public boolean canBePlaced(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canBePlacedInWorld(ItemStack stack, Player player, Level level, BlockPos pos, Direction direction) {
        return true; // just blind trust on this one. Recommended you test this.
    }

}
