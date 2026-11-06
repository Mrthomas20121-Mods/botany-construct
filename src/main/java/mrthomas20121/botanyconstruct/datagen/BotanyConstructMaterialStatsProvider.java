package mrthomas20121.botanyconstruct.datagen;

import mrthomas20121.botanyconstruct.BotanyConstructMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.*;

public class BotanyConstructMaterialStatsProvider extends AbstractMaterialStatsDataProvider {

    public BotanyConstructMaterialStatsProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialStats() {
        addMelee();
        addRanged();
        addArmor();
    }

    private void addMelee() {

        // manasteel has iron stats in botania so it does here too
        addMaterialStats(BotanyConstructMaterialIds.MANASTEEL,
                new HeadMaterialStats(250, 6f, IRON, 2f),
                HandleMaterialStats.multipliers().durability(1.10f).build(),
                StatlessMaterialStats.BINDING);

        addMaterialStats(BotanyConstructMaterialIds.TERRASTEEL,
                new HeadMaterialStats(800, 7f, DIAMOND, 2.25f),
                HandleMaterialStats.multipliers().durability(1.05f).miningSpeed(1.05f).attackSpeed(1.05f).build(),
                StatlessMaterialStats.BINDING);

        addMaterialStats(BotanyConstructMaterialIds.ELEMENTIUM,
                new HeadMaterialStats(800, 6f, DIAMOND, 2.5f),
                HandleMaterialStats.multipliers().durability(1.05f).miningSpeed(1.05f).attackSpeed(1.05f).build(),
                StatlessMaterialStats.BINDING);
    }

    private void addRanged() {
        addMaterialStats(BotanyConstructMaterialIds.MANA_INFUSED_STRING, StatlessMaterialStats.BOWSTRING, StatlessMaterialStats.FLETCHING);
    }

    private void addArmor() {

        addArmorShieldStats(BotanyConstructMaterialIds.MANASTEEL, PlatingMaterialStats.builder().durabilityFactor(15).armor(2, 4, 5, 2), StatlessMaterialStats.MAILLE);
        addArmorShieldStats(BotanyConstructMaterialIds.TERRASTEEL, PlatingMaterialStats.builder().durabilityFactor(45).armor(2, 5, 7, 2).toughness(1).knockbackResistance(0.05f), StatlessMaterialStats.MAILLE);
        addArmorShieldStats(BotanyConstructMaterialIds.ELEMENTIUM, PlatingMaterialStats.builder().durabilityFactor(55).armor(2, 5, 7, 2).toughness(2).knockbackResistance(0.1f), StatlessMaterialStats.MAILLE);
    }

    @Override
    public String getName() {
        return "Botania Material Stats";
    }
}
