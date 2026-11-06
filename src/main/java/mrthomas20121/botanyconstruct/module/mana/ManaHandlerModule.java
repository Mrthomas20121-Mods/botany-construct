package mrthomas20121.botanyconstruct.module.mana;

import mrthomas20121.botanyconstruct.BotanyConstructModifierIds;
import mrthomas20121.botanyconstruct.module.ToolManaCapability;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.utils.Util;

import java.util.List;
import java.util.Objects;

/**
 * Module for the handling of manaReduction on tools
 * @param maxMana the max mana on the tool
 */
public record ManaHandlerModule(LevelingInt maxMana) implements ModifierModule, DisplayNameModifierHook, ValidateModifierHook, ModifierRemovalHook, ToolStatsModifierHook {

    private static final List<ModuleHook<?>> DEFAULT_HOOKS = List.of(ModifierHooks.DISPLAY_NAME, ModifierHooks.VALIDATE, ModifierHooks.REMOVE);

    public static final RecordLoadable<ManaHandlerModule> LOADER = RecordLoadable.create(LevelingInt.LOADABLE.requiredField("manaReduction", ManaHandlerModule::maxMana), ManaHandlerModule::new);

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
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolManaCapability.MAX_STAT.add(builder, maxMana.compute(modifier.getEffectiveLevel()));
    }

    @Override
    public @NotNull Component getDisplayName(IToolStackView tool, ModifierEntry entry, Component name, @Nullable RegistryAccess access) {
        return Component.translatable(getTranslationKey() + ".current")
                .append(ToolManaCapability.MAX_STAT.formatContents(ToolManaCapability.getMana(tool), ToolManaCapability.getMaxMana(tool)));
    }

    @Override
    public @Nullable Component validate(IToolStackView tool, ModifierEntry modifier) {
        ToolManaCapability.checkMana(tool);
        return null;
    }

    @Override
    public @Nullable Component onRemoved(IToolStackView tool, Modifier modifier) {
        ToolManaCapability.checkMana(tool);
        return null;
    }

    private String getTranslationKey() {
        return Util.makeTranslationKey("modifier", Objects.requireNonNull(BotanyConstructModifierIds.MANA_STORAGE));
    }
}
