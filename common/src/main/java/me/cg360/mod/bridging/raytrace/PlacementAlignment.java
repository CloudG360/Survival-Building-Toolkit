package me.cg360.mod.bridging.raytrace;

import me.cg360.mod.bridging.BridgingMod;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

/**
 * Used to determine the indicator that should be
 * used when bridging assist is available.
 */
public enum PlacementAlignment {

    UP("up"),
    DOWN("down"),
    HORIZONTAL("horizontal");

    private final ResourceLocation textureLocation;

    PlacementAlignment(String textureName) {
        this.textureLocation = ResourceLocation.tryBuild(BridgingMod.MOD_ID, "indicator/%s".formatted(textureName));
    }

    public ResourceLocation getTexturePath() {
        return this.textureLocation;
    }

    public static PlacementAlignment from(Direction direction) {
        if(direction == null) return null;

        return switch (direction) {
            case UP -> DOWN;
            case DOWN -> UP;
            default -> HORIZONTAL;
        };
    }

}
