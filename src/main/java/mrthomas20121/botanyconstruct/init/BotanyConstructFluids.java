package mrthomas20121.botanyconstruct.init;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.common.registration.FluidDeferredRegisterExtension;

import static slimeknights.tconstruct.fluids.block.BurningLiquidBlock.createBurning;

public class BotanyConstructFluids {
    
    public static FluidDeferredRegisterExtension FLUIDS = new FluidDeferredRegisterExtension(BotanyConstruct.MOD_ID);

    public static final FlowingFluidObject<ForgeFlowingFluid> moltenManasteel = FLUIDS.registerMetal("molten_manasteel").type(hot("molten_manasteel").temperature(1100).lightLevel(12)).block(createBurning(MapColor.COLOR_LIGHT_BLUE, 12, 10, 5f)).bucket().commonTag().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenTerrasteel = FLUIDS.registerMetal("molten_terrasteel").type(hot("molten_terrasteel").temperature(1100).lightLevel(12)).block(createBurning(MapColor.COLOR_LIGHT_GREEN, 12, 10, 5f)).bucket().commonTag().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenElementium = FLUIDS.registerMetal("molten_elementium").type(hot("molten_elementium").temperature(1100).lightLevel(12)).block(createBurning(MapColor.COLOR_PINK, 12, 10, 5f)).bucket().commonTag().flowing();

    /** Creates a builder for a hot with sounds and description */
    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .descriptionId(BotanyConstruct.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                // from forge lava type
                .motionScale(0.0023333333333333335D)
                .canSwim(false).canDrown(false)
                .pathType(BlockPathTypes.LAVA).adjacentPathType(null);
    }
}
