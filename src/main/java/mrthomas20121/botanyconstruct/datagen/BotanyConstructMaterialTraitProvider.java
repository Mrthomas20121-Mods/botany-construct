package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import mrthomas20121.botanyconstruct.BotanyConstructModifierIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class BotanyConstructMaterialTraitProvider extends AbstractMaterialTraitDataProvider {

    public BotanyConstructMaterialTraitProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialTraits() {

        addTraits(BotanyConstructMaterialIds.MANASTEEL, MaterialRegistry.MELEE_HARVEST, BotanyConstructModifierIds.MANA_STORAGE);
        addTraits(BotanyConstructMaterialIds.TERRASTEEL, MaterialRegistry.MELEE_HARVEST, BotanyConstructModifierIds.MANA_STORAGE, BotanyConstructModifierIds.TERRESTRIAL);
        addTraits(BotanyConstructMaterialIds.ELEMENTIUM, MaterialRegistry.MELEE_HARVEST, BotanyConstructModifierIds.MANA_STORAGE, BotanyConstructModifierIds.MYTHOLOGICAL);

        addTraits(BotanyConstructMaterialIds.MANASTEEL, MaterialRegistry.ARMOR, BotanyConstructModifierIds.MANA_AFFINITY);
        addTraits(BotanyConstructMaterialIds.TERRASTEEL, MaterialRegistry.ARMOR, BotanyConstructModifierIds.TERRA_AFFINITY);
        addTraits(BotanyConstructMaterialIds.ELEMENTIUM, MaterialRegistry.ARMOR, BotanyConstructModifierIds.TERRA_AFFINITY);

        addTraits(BotanyConstructMaterialIds.MANA_INFUSED_STRING, StatlessMaterialStats.BOWSTRING.getIdentifier(), BotanyConstructModifierIds.MANA_STORAGE);
    }

    @Override
    public String getName() {
        return "Thermal Material Traits";
    }
}
