package mrthomas20121.botanyconstruct;

import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class BotanyConstructMaterialIds {

    public static MaterialId MANASTEEL = create("manasteel");
    public static MaterialId TERRASTEEL = create("terrasteel");
    public static MaterialId ELEMENTIUM = create("elementium");
    public static MaterialId MANA_INFUSED_STRING = create("mana_infused_string");

    // public static MaterialId LIVINGWOOD = create("livingwood");
    // public static MaterialId LIVINGROCK = create("livingrock");
    // public static MaterialId DREAMWOOD = create("dreamwood");

    public static MaterialId create(String name) {
        return new MaterialId(BotanyConstruct.MOD_ID, name);
    }
}
