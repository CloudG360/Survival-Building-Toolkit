package me.cg360.mod.bridging.compat;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.TrapDoorBlock;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class BridgingCrosshairTweaks {

    // Could replace this with a lil' queue?
    // might over-complicate it so have a boolean.
    public static boolean forceHidden = false;

    public static int yShift = 0;



    // If there's a block that isn't handled by slab assist but should be,
    // add a filter to the list.
    //TODO: Implement in SpecialHandlers
    public static List<Function<Block, Boolean>> slabAssistFilters = new LinkedList<>();
    static {
        slabAssistFilters.add(block -> block instanceof SlabBlock);
        slabAssistFilters.add(block -> block instanceof TrapDoorBlock);
    }
}
