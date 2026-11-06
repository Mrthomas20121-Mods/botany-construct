package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import mrthomas20121.botanyconstruct.init.BotanyConstructFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import vazkii.botania.common.item.BotaniaItems;

import java.util.function.Consumer;

public class BotaniaConstructRecipeProvider extends RecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper, ISmelteryRecipeHelper, ICommonRecipeHelper {

    public BotaniaConstructRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        String materialFolder = "materials/";
        String smelteryFolder = "smeltery/";

        // mana infused
        materialRecipe(consumer, BotanyConstructMaterialIds.MANA_INFUSED_STRING, Ingredient.of(BotaniaItems.manaString), 1, 1, materialFolder+"/mana_infused_string");

        metalMaterialRecipe(consumer, BotanyConstructMaterialIds.MANASTEEL, "manasteel", BotanyConstructFluids.moltenManasteel, materialFolder, smelteryFolder);
        metalMaterialRecipe(consumer, BotanyConstructMaterialIds.TERRASTEEL, "terrasteel", BotanyConstructFluids.moltenTerrasteel, materialFolder, smelteryFolder);
        metalMaterialRecipe(consumer, BotanyConstructMaterialIds.ELEMENTIUM, "elementium", BotanyConstructFluids.moltenElementium, materialFolder, smelteryFolder);
    }

    private void metalMaterialRecipe(Consumer<FinishedRecipe> consumer, MaterialId id, String name, FlowingFluidObject<ForgeFlowingFluid> fluid, String materialFolder, String smelteryFolder) {
        metalMaterialRecipe(consumer, id, materialFolder, name, false);
        materialMeltingCasting(consumer, id, fluid, smelteryFolder);
    }

    protected SmelteryRecipeBuilder smelteryBuilder(Consumer<FinishedRecipe> consumer, String folder, Fluid fluid, String materialName) {
        return SmelteryRecipeBuilder.fluid(consumer, location(materialName), fluid).meltingFolder(folder);
    }

    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, String name, TagKey<Fluid> fluid) {
        return SmelteryRecipeBuilder.fluid(consumer, location(name), fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }

    /** Creates a smeltery builder for a metal fluid */
    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, FluidObject<?> fluid) {
        return molten(consumer, fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }

    public ResourceLocation merge(ResourceLocation loc, String toAdd) {
        return new ResourceLocation(loc.getNamespace(), loc.getPath()+toAdd);
    }

    public void castRecipe(Consumer<FinishedRecipe> consumer,  ItemLike cast, Ingredient input, String folder, String name) {
        ItemCastingRecipeBuilder.tableRecipe(cast)
                .setFluidAndTime(TinkerFluids.moltenBronze, FluidValues.INGOT*4)
                .setCast(input, true)
                .setSwitchSlots()
                .save(consumer, location(folder + "gold/" + name));
    }

    @Override
    public String getModId() {
        return BotanyConstruct.MOD_ID;
    }
}
