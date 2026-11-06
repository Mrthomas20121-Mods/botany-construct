package mrthomas20121.botanyconstruct.module.mana;

import mrthomas20121.botanyconstruct.module.ToolManaCapability;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.special.CapacityBarHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

/** Module connecting Botania mana to {@link CapacityBarHook}. Meant to be used on the specific modifier rather than an internal modifier. */
public enum ManaAsCapacityModule implements ModifierModule, CapacityBarHook {
    INSTANCE;

    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<slimeknights.tconstruct.library.modifiers.modules.capacity.EnergyAsCapacityModule>defaultHooks(ModifierHooks.CAPACITY_BAR);
    public static final RecordLoadable<ManaAsCapacityModule> LOADER = new SingletonLoader<>(INSTANCE);

    @Override
    public @NotNull RecordLoadable<ManaAsCapacityModule> getLoader() {
        return LOADER;
    }

    @Override
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public int getAmount(IToolStackView tool) {
        return ToolManaCapability.getMana(tool);
    }

    @Override
    public int getCapacity(IToolStackView tool, ModifierEntry entry) {
        return ToolManaCapability.getMaxMana(tool);
    }

    @Override
    public void setAmount(IToolStackView tool, ModifierEntry entry, int amount) {
        ToolManaCapability.setMana(tool, amount);
    }

    @Override
    public void addAmount(IToolStackView tool, ModifierEntry modifier, int amount) {
        ToolManaCapability.addMana(tool, amount);
    }

    @Override
    public void removeAmount(IToolStackView tool, ModifierEntry modifier, int amount) {
        ToolManaCapability.addMana(tool, -amount);
    }
}
