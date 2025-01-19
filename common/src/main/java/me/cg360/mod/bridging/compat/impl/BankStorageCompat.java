package me.cg360.mod.bridging.compat.impl;

import me.cg360.mod.bridging.compat.SpecialHandlers;
import me.cg360.mod.bridging.compat.handler.PlaceableItemHandler;
import net.minecraft.resources.ResourceLocation;

public class BankStorageCompat {

    public BankStorageCompat() {
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_1"), PlaceableItemHandler.INSTANCE);
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_2"), PlaceableItemHandler.INSTANCE);
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_3"), PlaceableItemHandler.INSTANCE);
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_4"), PlaceableItemHandler.INSTANCE);
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_5"), PlaceableItemHandler.INSTANCE);
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_6"), PlaceableItemHandler.INSTANCE);
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_7"), PlaceableItemHandler.INSTANCE);
        SpecialHandlers.registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("bankstorage", "bank_link"), PlaceableItemHandler.INSTANCE);
    }

}
