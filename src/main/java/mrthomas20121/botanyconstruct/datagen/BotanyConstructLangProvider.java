package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import mrthomas20121.botanyconstruct.BotanyConstructModifierIds;
import mrthomas20121.botanyconstruct.init.BotanyConstructFluids;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class BotanyConstructLangProvider extends LanguageProvider {

    public BotanyConstructLangProvider(PackOutput output) {
        super(output, BotanyConstruct.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        fluid(BotanyConstructFluids.moltenManasteel, "Manasteel", "Mana Fireball! Mana Fireball!");
        fluid(BotanyConstructFluids.moltenTerrasteel, "Terrasteel", "I cast mana drain!");
        fluid(BotanyConstructFluids.moltenElementium, "Elementium", "Go Pixie Go!");

        addMaterial(BotanyConstructMaterialIds.MANASTEEL, "Manasteel");
        addMaterialFlavor(BotanyConstructMaterialIds.MANASTEEL, "Iron if it was cool.");
        addMaterial(BotanyConstructMaterialIds.TERRASTEEL, "Lumium");
        addMaterialFlavor(BotanyConstructMaterialIds.TERRASTEEL, "Earth N°1 best material.");
        addMaterial(BotanyConstructMaterialIds.ELEMENTIUM, "Elementium");
        addMaterialFlavor(BotanyConstructMaterialIds.ELEMENTIUM, "One does not simply summon a pixie.");

        addModifier(BotanyConstructModifierIds.MANA_STORAGE, "Mana Storage");
        addModifierFlavor(BotanyConstructModifierIds.MANA_STORAGE, "Captain Falcon would be proud of the color of the mana");
        addModifierDesc(BotanyConstructModifierIds.MANA_STORAGE, "Store mana, consume it instead of durability.");

        addModifier(BotanyConstructModifierIds.TERRESTRIAL, "Terrestrial");
        addModifierFlavor(BotanyConstructModifierIds.TERRESTRIAL, "Magic is Life, Magic is Love.");
        addModifierDesc(BotanyConstructModifierIds.TERRESTRIAL, "Shoot a magic bullet.");

        addModifier(BotanyConstructModifierIds.MYTHOLOGICAL, "Mythological");
        addModifierFlavor(BotanyConstructModifierIds.MYTHOLOGICAL, "We must have more! 500 pixies.");
        addModifierDesc(BotanyConstructModifierIds.MYTHOLOGICAL, "Has a chance to spawn a pixie.");
    }

    public void fluid(FluidObject<ForgeFlowingFluid> fluid, String name) {
        add("fluid."+ BotanyConstruct.MOD_ID+"." + fluid.getId().getPath(), name);
        add(fluid.asItem(), name + " Bucket");
    }

    public void fluid(FluidObject<ForgeFlowingFluid> fluid, String name, String fluidEffect) {
        add("fluid."+ BotanyConstruct.MOD_ID+"." + fluid.getId().getPath(), name);
        add("fluid."+ BotanyConstruct.MOD_ID+"." + fluid.getId().getPath() + ".fluid_effect", fluidEffect);
        add(fluid.asItem(), name + " Bucket");
    }


    public void addModifier(ModifierId material, String s) {
        add("modifier."+material.getNamespace()+"."+material.getPath(), s);
    }

    public void addModifierFlavor(ModifierId material, String s) {
        add("modifier."+material.getNamespace()+"."+material.getPath()+".flavor", s);
    }

    public void addModifierDesc(ModifierId material, String s) {
        add("modifier."+material.getNamespace()+"."+material.getPath()+".description", s);
    }

    public void addMaterial(MaterialId material, String s) {
        add("material."+material.getNamespace()+"."+material.getPath(), s);
    }
    public void addMaterialFlavor(MaterialId material, String s) {
        add("material."+material.getNamespace()+"."+material.getPath()+".flavor", s);
    }

}
