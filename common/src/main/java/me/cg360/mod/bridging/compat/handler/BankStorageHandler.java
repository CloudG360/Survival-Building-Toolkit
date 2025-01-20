package me.cg360.mod.bridging.compat.handler;

import com.mojang.logging.LogUtils;
import me.cg360.mod.bridging.building.Bridge;
import me.cg360.mod.bridging.compat.SpecialBridgingHandler;
import me.cg360.mod.bridging.util.GameSupport;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.natte.bankstorage.BankStorage;
import net.natte.bankstorage.container.BankItemStorage;
import net.natte.bankstorage.options.BankOptions;
import net.natte.bankstorage.util.Util;

/**
 * Literally only needed for slab & torch support. Unboxes the item held by the bank.
 * WARNING: Only load when BankStorage is present.
 */
public class BankStorageHandler implements SpecialBridgingHandler {

    public static final BankStorageHandler INSTANCE = new BankStorageHandler();

    @Override
    public boolean canBePlaced(ItemStack stack) {
        Item item = stack.getItem();

        // TODO: Look for ItemComponents instead of an item type.
        if(!(Util.isBankLike(stack))) {
            // not a dank bank. This compatibility class does nothing!
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            LogUtils.getLogger().warn("Blocked using BankStorage compatibility for an unsupported item (%s)!".formatted(id));
            return false;
        }

        BankOptions options = Util.getOrCreateOptions(stack);
        if(!options.buildMode().isActive()) {
            return false;
        }

        BankItemStorage storage = Util.getBankItemStorage(stack);
        if(storage == null) {
            return false;
        }

        int slot = stack.getOrDefault(BankStorage.SelectedSlotComponentType, 0);
        ItemStack containedStack = storage.getSelectedItem(slot);

        if(containedStack.isEmpty())
            return false;

        return GameSupport.passesDefaultPlacementCheck(containedStack);
    }

    @Override
    public boolean canBePlacedInWorld(ItemStack stack, Player player, Level level, BlockPos pos, Direction direction) {
        BankItemStorage storage = Util.getBankItemStorage(stack);
        if(storage == null) {
            return false;
        }

        int slot = stack.getOrDefault(BankStorage.SelectedSlotComponentType, 0);
        ItemStack containedStack = storage.getSelectedItem(slot);
        if(containedStack.isEmpty())
            return false;

        return player.mayUseItemAt(pos, direction, containedStack);
    }

    @Override
    public BlockHitResult generatePlacementTarget(ItemStack stack, Player player, Level level, Direction direction, BlockPos pos) {
        BankItemStorage storage = Util.getBankItemStorage(stack);
        if(storage == null) {
            return null;
        }

        int slot = stack.getOrDefault(BankStorage.SelectedSlotComponentType, 0);
        ItemStack containedStack = storage.getSelectedItem(slot);

        return Bridge.getDefaultPlaceAssistTarget(containedStack, level, direction, pos);
    }

}
