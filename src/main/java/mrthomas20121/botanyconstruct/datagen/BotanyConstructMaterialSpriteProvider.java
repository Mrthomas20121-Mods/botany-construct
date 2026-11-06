package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class BotanyConstructMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Override
    public String getName() {
        return "Botania Material Sprite Provider";
    }

    @Override
    protected void addAllMaterials() {

        buildMaterial(BotanyConstructMaterialIds.MANASTEEL)
                .meleeHarvest().armor().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF160539).addARGB(102, 0xFF25128A).addARGB(140, 0xFF1F20B9).addARGB(178, 0xFF3A63DA).addARGB(216, 0xFF67B9EE).addARGB(255, 0xFFB5FFFC).build());

        buildMaterial(BotanyConstructMaterialIds.TERRASTEEL)
                .meleeHarvest().armor().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF001E11).addARGB(102, 0xFF043C1C).addARGB(140, 0xFF0C7227).addARGB(178, 0xFF2BB93B).addARGB(216, 0xFF6AE862).addARGB(255, 0xFFCCFFB5).build());

        buildMaterial(BotanyConstructMaterialIds.ELEMENTIUM)
                .meleeHarvest().armor().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF150136).addARGB(102, 0xFF3F0766).addARGB(140, 0xFF7A1892).addARGB(178, 0xFFC543A8).addARGB(216, 0xFFE084A5).addARGB(255, 0xFFF5C7C4).build());

        buildMaterial(BotanyConstructMaterialIds.MANA_INFUSED_STRING)
                .fletching().statType(StatlessMaterialStats.BOWSTRING)
                .fallbacks("cloth")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF00324C).addARGB(102, 0xFF004B67).addARGB(140, 0xFF00738B).addARGB(178, 0xFF7ED9D5).addARGB(216, 0xFFADFFF9).addARGB(255, 0xFFDBFFFA).build());
    }
}
