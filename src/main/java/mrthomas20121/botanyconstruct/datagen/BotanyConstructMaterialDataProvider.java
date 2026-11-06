package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class BotanyConstructMaterialDataProvider extends AbstractMaterialDataProvider {

    public BotanyConstructMaterialDataProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addMaterials() {
        //material(BotanyConstructMaterialIds.LIVINGWOOD).craftable(true).tier(2).sort(ORDER_COMPAT);
        //material(BotanyConstructMaterialIds.LIVINGROCK).craftable(true).tier(2).sort(ORDER_COMPAT);
        material(BotanyConstructMaterialIds.MANASTEEL).tier(3).sort(ORDER_COMPAT);
        material(BotanyConstructMaterialIds.TERRASTEEL).tier(3).sort(ORDER_COMPAT);
        material(BotanyConstructMaterialIds.ELEMENTIUM).tier(4).sort(ORDER_COMPAT);
        material(BotanyConstructMaterialIds.MANA_INFUSED_STRING).tier(1).sort(ORDER_COMPAT);
    }

    @Override
    public String getName() {
        return "Botany Construct Material Data Provider";
    }
}
