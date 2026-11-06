package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import mrthomas20121.botanyconstruct.init.BotanyConstructFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.registration.object.FlowingFluidObject;

import java.util.concurrent.CompletableFuture;

public class BotanyConstructFluidTagProvider extends FluidTagsProvider {

    public BotanyConstructFluidTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, BotanyConstruct.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        fluidTag(BotanyConstructFluids.moltenManasteel);
        fluidTag(BotanyConstructFluids.moltenTerrasteel);
        fluidTag(BotanyConstructFluids.moltenElementium);
    }

    /** Adds tags for a placable fluid */
    private void fluidTag(FlowingFluidObject<?> fluid) {
        tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
        TagKey<Fluid> tag = fluid.getCommonTag();
        if (tag != null) {
            tag(tag).addTag(fluid.getLocalTag());
        }
    }
}
