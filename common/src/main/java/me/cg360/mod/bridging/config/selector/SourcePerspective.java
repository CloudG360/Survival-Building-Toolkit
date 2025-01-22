package me.cg360.mod.bridging.config.selector;

import me.cg360.mod.bridging.config.helper.Translatable;

/**
 * A compromise for issues:
 * <a href="https://github.com/squeeglii/BridgingMod/issues/22">...</a>
 * <a href="https://github.com/squeeglii/BridgingMod/issues/15">...</a>
 */
public enum SourcePerspective implements Translatable {

    /**
     * Lets bridging mod choose between the other options for mod compatibility.
     * - without compat: COPY_TOGGLE_PERSPECTIVE
     * - with freelook: ALWAYS_EYELINE
     */
    LET_BRIDGING_MOD_DECIDE,

    /** Use the selected vanilla camera (i,e, change the camera used when F5 toggles perspective) */
    COPY_TOGGLE_PERSPECTIVE,

    /** Always use the 1st person camera. */
    ALWAYS_EYELINE;

    @Override
    public String getTranslationKey() {
        return "enum.bridgingmod.source_perspective.%s".formatted(this.name().toLowerCase());
    }

}
