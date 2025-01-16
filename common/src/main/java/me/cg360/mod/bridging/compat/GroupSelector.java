package me.cg360.mod.bridging.compat;

import net.minecraft.world.item.ItemStack;

public interface GroupSelector {

    boolean passes(ItemStack stack);

}
