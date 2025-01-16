package me.cg360.mod.bridging.compat;

import me.cg360.mod.bridging.compat.handler.PlaceableItemHandler;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class SpecialHandlers {

    // Item IDs have priority over groups. If an item has its own handler, it's more likely that
    // that's the intended handler.
    private static HashMap<ResourceLocation, SpecialBridgingHandler> specialHandlers = new HashMap<>();

    // Run through all the activation conditions.
    private static LinkedList<SpecialGroupHandlerEntry> specialHandlerGroups = new LinkedList<>();



    public static void registerSpecialHandler(ResourceLocation itemId, SpecialBridgingHandler handler) {
        specialHandlers.put(itemId, handler);
    }

    /* Items must be registered before this is possible. */
    public static void registerSpecialHandler(Item item, SpecialBridgingHandler handler) {
        ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(item);
        specialHandlers.put(itemKey, handler);
    }

    public static void registerSpecialGroupHandler(SpecialBridgingHandler handler, GroupSelector item) {
        SpecialGroupHandlerEntry entry = new SpecialGroupHandlerEntry(handler, item);
        specialHandlerGroups.add(entry);
    }


    public static Optional<SpecialBridgingHandler> getSpecialHandler(ItemStack itemStack) {
        ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(itemStack.getItem());

        if(specialHandlers.containsKey(itemKey)) {
            return Optional.of(specialHandlers.get(itemKey));
        }

        // If there's a group handler which has a selector that cover's this item, return that.
        // otherwise, nothing! :D
        return specialHandlerGroups.stream()
                .filter(groupHandler -> groupHandler.groupSelector.passes(itemStack))
                .findFirst()
                .map(entry -> entry.handler);
    }

    public static boolean hasSpecialHandler(ItemStack item) {
        ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(item.getItem());

        if(specialHandlers.containsKey(itemKey))
            return true;

        return specialHandlerGroups.stream()
                .map(SpecialGroupHandlerEntry::groupSelector)
                .anyMatch(selector -> selector.passes(item));
    }

    // default handlers.
    static {

        // buckets should be placeable!  -- unfortunately, the bucket item uses player pov *inside* the bucket item.
        //registerSpecialGroupHandler(PlaceableItemHandler.INSTANCE, item -> {
        //    return item.getItem() instanceof BucketItem;
        //});

        // Compatibility - Storage mods.
        // Would be better to check if the storage was in placement mode, but whitelisting them blindly is just easier.
        registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("dankstorage", "dank_1"), PlaceableItemHandler.INSTANCE);
        registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("dankstorage", "dank_2"), PlaceableItemHandler.INSTANCE);
        registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("dankstorage", "dank_3"), PlaceableItemHandler.INSTANCE);
        registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("dankstorage", "dank_4"), PlaceableItemHandler.INSTANCE);
        registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("dankstorage", "dank_5"), PlaceableItemHandler.INSTANCE);
        registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("dankstorage", "dank_6"), PlaceableItemHandler.INSTANCE);
        registerSpecialHandler(ResourceLocation.fromNamespaceAndPath("dankstorage", "dank_7"), PlaceableItemHandler.INSTANCE);
    }

    // Group selector is NOT the placement condition.
    // Group selector just checks whether a block should have special rules *CONSIDERED*.
    public record SpecialGroupHandlerEntry(SpecialBridgingHandler handler, GroupSelector groupSelector) {}

}
