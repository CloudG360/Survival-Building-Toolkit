package me.cg360.mod.bridging.entrypoint.fabric;

import me.cg360.mod.bridging.BridgingKeyMappings;
import me.cg360.mod.bridging.BridgingMod;
import me.cg360.mod.bridging.compat.impl.DankStorageCompat;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;

public class BridgingModFabric {

    public void init() {
        BridgingKeyMappings.forEachKeybindingDo(KeyBindingHelper::registerKeyBinding);
        BridgingMod.init();

        if(FabricLoader.getInstance().isModLoaded("dankstorage")) {
            new DankStorageCompat();
        }
    }

}
