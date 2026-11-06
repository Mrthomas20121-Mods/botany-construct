package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstructModifierIds;
import mrthomas20121.botanyconstruct.module.mana.ManaAffinityModule;
import mrthomas20121.botanyconstruct.module.mana.ManaHandlerModule;
import mrthomas20121.botanyconstruct.module.mana.TerrestrialModule;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.modifiers.modules.behavior.AttributeModule;
import vazkii.botania.common.handler.PixieHandler;

public class BotanyConstructModifierProvider extends AbstractModifierProvider {

    public BotanyConstructModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {

        buildModifier(BotanyConstructModifierIds.MANA_STORAGE)
                .addModule(new ManaHandlerModule(new LevelingInt(60, 20)));

        buildModifier(BotanyConstructModifierIds.MANA_AFFINITY)
                .addModule(new ManaAffinityModule(0.1f));

        buildModifier(BotanyConstructModifierIds.TERRA_AFFINITY)
                .addModule(new ManaAffinityModule(0.2f));

        buildModifier(BotanyConstructModifierIds.TERRESTRIAL)
                .addModule(new TerrestrialModule(new LevelingInt(100, 10)));

        buildModifier(BotanyConstructModifierIds.MYTHOLOGICAL)
                .addModule(AttributeModule.builder(PixieHandler.PIXIE_SPAWN_CHANCE, AttributeModifier.Operation.ADDITION).eachLevel(0.05f));



    }

    @Override
    public String getName() {
        return "Thermal Modifier Provider";
    }
}
