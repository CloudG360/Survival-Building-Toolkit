package me.cg360.mod.bridging.config.selector;

/**
 * A compromise for issues:
 * <a href="https://github.com/squeeglii/BridgingMod/issues/22">...</a>
 * <a href="https://github.com/squeeglii/BridgingMod/issues/15">...</a>
 */
public enum TargetCamera {

    /** Use the selected vanilla camera (i,e, change the camera used when F5 toggles perspective) */
    COPY_TOGGLE_PERSPECTIVE,

    /** Always use the 1st person camera. */
    ALWAYS_EYELINE

}
