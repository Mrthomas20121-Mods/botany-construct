package mrthomas20121.botanyconstruct.module.mana;

import mrthomas20121.botanyconstruct.BotanyConstructModifierIds;
import mrthomas20121.botanyconstruct.module.ToolManaCapability;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ValidateModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DisplayNameModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.Util;

import java.util.List;
import java.util.Objects;

/**
 * Module for the handling of manaReduction on tools
 * @param manaReduction the max mana on the tool
 */
public record ManaAffinityModule(float manaReduction) implements ModifierModule, ValidateModifierHook, ModifierRemovalHook {

    private static final List<ModuleHook<?>> DEFAULT_HOOKS = List.of(ModifierHooks.DISPLAY_NAME, ModifierHooks.VALIDATE, ModifierHooks.REMOVE);

    public static final RecordLoadable<ManaAffinityModule> LOADER = RecordLoadable.create(FloatLoadable.range(0, 1).requiredField("manaReduction", ManaAffinityModule::manaReduction), ManaAffinityModule::new);

    @Override
    public @NotNull RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public @NotNull Integer getPriority() {
        return 299;
    }

    @Override
    public @Nullable Component validate(IToolStackView tool, ModifierEntry modifier) {
        if(!tool.getPersistentData().contains(ToolManaCapability.MANA_REDUCTION_KEY)) {
            tool.getPersistentData().putFloat(ToolManaCapability.MANA_REDUCTION_KEY, manaReduction);
        }
        return null;
    }

    @Override
    public @Nullable Component onRemoved(IToolStackView tool, Modifier modifier) {
        if(tool.getPersistentData().contains(ToolManaCapability.MANA_REDUCTION_KEY)) {
            tool.getPersistentData().remove(ToolManaCapability.MANA_REDUCTION_KEY);
        }
        return null;
    }
}
