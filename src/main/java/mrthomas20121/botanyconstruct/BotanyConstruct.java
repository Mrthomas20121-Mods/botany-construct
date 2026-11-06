package mrthomas20121.botanyconstruct;

import mrthomas20121.botanyconstruct.datagen.BotanyConstructDatagen;
import mrthomas20121.botanyconstruct.init.BotanyConstructFluids;
import mrthomas20121.botanyconstruct.module.ToolManaCapability;
import mrthomas20121.botanyconstruct.module.mana.ManaAffinityModule;
import mrthomas20121.botanyconstruct.module.mana.ManaAsCapacityModule;
import mrthomas20121.botanyconstruct.module.mana.ManaHandlerModule;
import mrthomas20121.botanyconstruct.module.mana.TerrestrialModule;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider;

@Mod(BotanyConstruct.MOD_ID)
public class BotanyConstruct {

	public static final String MOD_ID = "botanyconstruct";

	public static String makeDescriptionId(String type, String name) {
		return type + "." + MOD_ID + "." + name;
	}

	public static ResourceLocation getResource(String name) {
		return new ResourceLocation(MOD_ID, name);
	}

	public BotanyConstruct() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		BotanyConstructFluids.FLUIDS.register(bus);

		bus.addListener(EventPriority.NORMAL, false, GatherDataEvent.class, BotanyConstructDatagen::init);
		bus.addListener(this::register);
		bus.addListener(this::commonEvent);
	}

	private void register(RegisterEvent event) {
		if(event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {

			ModifierModule.LOADER.register(new ResourceLocation(BotanyConstruct.MOD_ID, "terrestrial"), TerrestrialModule.LOADER);
			ModifierModule.LOADER.register(new ResourceLocation(BotanyConstruct.MOD_ID, "mana_handler"), ManaHandlerModule.LOADER);
			ModifierModule.LOADER.register(new ResourceLocation(BotanyConstruct.MOD_ID, "mana_as_capacity"), ManaAsCapacityModule.LOADER);
			ModifierModule.LOADER.register(new ResourceLocation(BotanyConstruct.MOD_ID, "mana_affinity"), ManaAffinityModule.LOADER);
		}
	}

	private void commonEvent(FMLCommonSetupEvent event) {
		ToolCapabilityProvider.register((stack, tool) -> new ToolManaCapability.Provider(tool));
	}
}
