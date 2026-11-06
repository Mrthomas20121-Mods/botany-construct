package mrthomas20121.botanyconstruct;

import slimeknights.tconstruct.library.modifiers.ModifierId;

public class BotanyConstructModifierIds {

    public static ModifierId MANA_STORAGE = create("mana_storage");
    public static ModifierId MANA_AFFINITY = create("mana_affinity");
    public static ModifierId TERRA_AFFINITY = create("terra_affinity");
    public static ModifierId TERRESTRIAL = create("terrestrial");
    public static ModifierId MYTHOLOGICAL = create("mythological");

    public static ModifierId create(String name) {
        return new ModifierId(BotanyConstruct.MOD_ID, name);
    }
}
