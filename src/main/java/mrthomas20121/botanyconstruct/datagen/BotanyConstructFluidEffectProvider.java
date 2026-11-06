package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import mrthomas20121.botanyconstruct.init.BotanyConstructFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import slimeknights.tconstruct.library.data.tinkering.AbstractFluidEffectProvider;
import slimeknights.tconstruct.library.modifiers.fluid.block.PlaceBlockFluidEffect;
import slimeknights.tconstruct.library.recipe.FluidValues;
import vazkii.botania.common.block.BotaniaBlocks;

public class BotanyConstructFluidEffectProvider extends AbstractFluidEffectProvider {

    public BotanyConstructFluidEffectProvider(PackOutput packOutput) {
        super(packOutput, BotanyConstruct.MOD_ID);
    }

    @Override
    protected void addFluids() {
        addFluid(BotanyConstructFluids.moltenManasteel, FluidValues.INGOT)
                .magicDamage(2f)
                .addBlockEffect(new PlaceBlockFluidEffect(BotaniaBlocks.livingwood, SoundEvents.WOOD_PLACE));

        addFluid(BotanyConstructFluids.moltenTerrasteel, FluidValues.INGOT)
                .magicDamage(3f)
                .addBlockEffect(new PlaceBlockFluidEffect(BotaniaBlocks.livingrock, SoundEvents.STONE_PLACE));

        addFluid(BotanyConstructFluids.moltenElementium, FluidValues.INGOT)
                .magicDamage(3f)
                .addBlockEffect(new PlaceBlockFluidEffect(BotaniaBlocks.blazeQuartz, SoundEvents.STONE_PLACE));
    }

    @Override
    public String getName() {
        return "Botania Fluid Effects";
    }
}
