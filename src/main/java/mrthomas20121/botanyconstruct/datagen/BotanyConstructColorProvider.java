package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import mrthomas20121.botanyconstruct.BotanyConstructModifierIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class BotanyConstructColorProvider extends MantleColorGenerator {

    public BotanyConstructColorProvider(PackOutput packOutput) {
        super(packOutput, BotanyConstruct.MOD_ID);
    }

    @Override
    void addColors() {

        addMaterialAndModifiers(BotanyConstructMaterialIds.MANASTEEL, "#67B9EE", BotanyConstructModifierIds.MANA_STORAGE, BotanyConstructModifierIds.MANA_AFFINITY);
        addMaterialAndModifiers(BotanyConstructMaterialIds.TERRASTEEL, "#6AE862", BotanyConstructModifierIds.TERRESTRIAL, BotanyConstructModifierIds.TERRA_AFFINITY);
        addMaterialAndModifiers(BotanyConstructMaterialIds.ELEMENTIUM, "#E084A5", BotanyConstructModifierIds.MYTHOLOGICAL);

    }

    private void addMaterialAndModifiers(MaterialId mat, String color, ModifierId... modifiers) {
        addMaterial(mat, color);

        for (ModifierId id : modifiers) {
            addModifier(id, color);
        }
    }
}
