package me.cg360.mod.bridging;

import dev.isxander.yacl3.platform.YACLPlatform;
import me.cg360.mod.bridging.config.BridgingConfig;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class BridgingMod {

    public static final String MOD_ID = "bridgingmod";

    // Environment
    private static String cameraIncompatibility = null;

    // Not quite incompatible, but some config defaults need to be changed when a mod is detected.
    public static void noteIncompatibleCameraMod(String modId) {
        cameraIncompatibility = modId;
    }


    public static void init() {
        BridgingConfig.HANDLER.load();
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(BridgingMod.MOD_ID, name);
    }

    public static BridgingConfig getConfig() {
        return BridgingConfig.HANDLER.instance();
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(BridgingMod.class);
    }

    public static Path getDefaultConfigPath() {
        return YACLPlatform.getConfigDir();
    }
}
