package me.cg360.mod.bridging.compat;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModList;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class BridgingModMixinPlugin implements IMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        Logger logger = LogUtils.getLogger();

        if(mixinClassName.equals("me.cg360.mod.bridging.mixin.compat.DankStorageMixin")) {
            boolean dankStorageCompatEnabled = ModList.get().isLoaded("dankstorage");
            logger.info("DankStorage [fix placement alignment]: "+(dankStorageCompatEnabled?"Enabled":"Disabled"));

            return dankStorageCompatEnabled;
        }

        return true;
    }


    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

}
