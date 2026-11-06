package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import slimeknights.tconstruct.fluids.data.FluidBlockstateModelProvider;
import slimeknights.tconstruct.fluids.data.FluidBucketModelProvider;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.util.concurrent.CompletableFuture;

public class BotanyConstructDatagen {

    public static void init(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        TinkerPartSpriteProvider partSpriteProvider = new TinkerPartSpriteProvider();
        BotanyConstructMaterialSpriteProvider materialSpriteProvider = new BotanyConstructMaterialSpriteProvider();
        BotanyConstructMaterialDataProvider materialDataProvider = new BotanyConstructMaterialDataProvider(packOutput);

        boolean server = event.includeServer();
        generator.addProvider(server, new BotanyConstructFluidTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(server, new BotaniaConstructRecipeProvider(packOutput));
        generator.addProvider(server, materialDataProvider);
        generator.addProvider(server, new BotanyConstructMaterialTraitProvider(packOutput, materialDataProvider));
        generator.addProvider(server, new BotanyConstructMaterialStatsProvider(packOutput, materialDataProvider));
        generator.addProvider(server, new BotanyConstructModifierProvider(packOutput));
        generator.addProvider(server, new BotanyConstructFluidEffectProvider(packOutput));

        boolean client = event.includeClient();
        generator.addProvider(client, new BotanyConstructColorProvider(packOutput));
        generator.addProvider(client, new MaterialPartTextureGenerator(packOutput, existingFileHelper, partSpriteProvider, materialSpriteProvider));
        generator.addProvider(client, new BotanyConstructMaterialRenderInfoProvider(packOutput, materialSpriteProvider, existingFileHelper));
        generator.addProvider(client, new BotanyConstructLangProvider(packOutput));
        generator.addProvider(client, new BotanyConstructFluidTextureProvider(packOutput));
        generator.addProvider(client, new FluidBucketModelProvider(packOutput, BotanyConstruct.MOD_ID));
        generator.addProvider(client, new FluidBlockstateModelProvider(packOutput, BotanyConstruct.MOD_ID));
    }
}
