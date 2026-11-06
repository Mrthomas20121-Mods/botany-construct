package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class BotanyConstructMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {

    public BotanyConstructMaterialRenderInfoProvider(PackOutput packOutput, @Nullable AbstractMaterialSpriteProvider materialSprites, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, materialSprites, existingFileHelper);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(BotanyConstructMaterialIds.MANASTEEL).color(0x67B9EE).fallbacks("metal");
        buildRenderInfo(BotanyConstructMaterialIds.TERRASTEEL).color(0x6AE862).fallbacks("metal");
        buildRenderInfo(BotanyConstructMaterialIds.ELEMENTIUM).color(0xE084A5).fallbacks("metal");
        buildRenderInfo(BotanyConstructMaterialIds.MANA_INFUSED_STRING).color(0xADFFF9);
    }

    @Override
    public String getName() {
        return "Botany Construct Material Render Info";
    }
}
