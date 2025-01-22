package me.cg360.mod.bridging.entrypoint.neoforge;

import me.cg360.mod.bridging.BridgingKeyMappings;
import me.cg360.mod.bridging.BridgingMod;
import me.cg360.mod.bridging.ModIds;
import me.cg360.mod.bridging.compat.impl.BankStorageCompat;
import me.cg360.mod.bridging.compat.impl.DankStorageCompat;
import me.cg360.mod.bridging.compat.impl.DynamicCrosshairCompat;
import me.cg360.mod.bridging.config.BridgingConfigUI;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = BridgingMod.MOD_ID, dist = Dist.CLIENT)
public class BridgingModNeoForge {

    private static final String DYNAMIC_CROSSHAIR_MOD = "dynamiccrosshair";

    public BridgingModNeoForge(IEventBus modEventBus) {
        modEventBus.addListener(this::init);
        modEventBus.addListener(this::registerBindings);
    }


    public void init(FMLClientSetupEvent event) {

        if(ModList.get().isLoaded(ModIds.FREE_LOOK))
            BridgingMod.noteIncompatibleMod(ModIds.FREE_LOOK); // this just enables extra compat code. It works.

        BridgingMod.init(); // loads config
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) -> BridgingConfigUI.buildConfig().generateScreen(parent)
        );

        if(ModList.get().isLoaded(DYNAMIC_CROSSHAIR_MOD))
            InterModComms.sendTo(DYNAMIC_CROSSHAIR_MOD, "register_api", DynamicCrosshairCompat::new);

        if(ModList.get().isLoaded("dankstorage")) {
            new DankStorageCompat();
        }

        if(ModList.get().isLoaded("bankstorage")) {
            new BankStorageCompat();
        }
    }


    public void registerBindings(RegisterKeyMappingsEvent event) {
        BridgingKeyMappings.forEachKeybindingDo(event::register);
    }

}
