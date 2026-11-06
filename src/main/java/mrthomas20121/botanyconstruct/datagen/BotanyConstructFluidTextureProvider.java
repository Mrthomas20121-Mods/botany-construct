package mrthomas20121.botanyconstruct.datagen;

import com.mojang.blaze3d.shaders.FogShape;
import mrthomas20121.botanyconstruct.BotanyConstruct;
import mrthomas20121.botanyconstruct.init.BotanyConstructFluids;
import net.minecraft.data.PackOutput;
import slimeknights.mantle.fluid.texture.AbstractFluidTextureProvider;
import slimeknights.mantle.fluid.texture.FluidTexture;
import slimeknights.mantle.registration.object.FluidObject;

import static slimeknights.tconstruct.TConstruct.getResource;
import static slimeknights.tconstruct.fluids.TinkerFluids.withoutMolten;

public class BotanyConstructFluidTextureProvider extends AbstractFluidTextureProvider {
    public BotanyConstructFluidTextureProvider(PackOutput packOutput) {
        super(packOutput, BotanyConstruct.MOD_ID);
    }

    @Override
    public void addTextures() {
        moltenFolder(BotanyConstructFluids.moltenManasteel, "molten/manasteel");
        moltenFolder(BotanyConstructFluids.moltenTerrasteel, "molten/terrasteel");
        moltenFolder(BotanyConstructFluids.moltenElementium, "molten/elementium");
    }

    @Override
    public String getName() {
        return "Botany Construct Fluid Texture Provider";
    }

    /** Creates a texture using the given fixed name in the fluid folder */
    private FluidTexture.Builder named(FluidObject<?> fluid, String name) {
        return texture(fluid).root(BotanyConstruct.getResource("fluid/"+name+"/"))
                .still().flowing().camera().calculateFogColor(true).fog(FogShape.SPHERE, 0.25f, 2);
    }

    private FluidTexture.Builder molten(FluidObject<?> fluid) {
        return named(fluid, "molten/" + withoutMolten(fluid));
    }

    /** Creates a texture in given subfolder of molten, stripping molten from the name */
    private FluidTexture.Builder moltenFolder(FluidObject<?> fluid, String folder) {
        return named(fluid, "molten/" + folder + "/" + withoutMolten(fluid));
    }
}
