package mrthomas20121.botanyconstruct.module.mana;

import mrthomas20121.botanyconstruct.module.ToolManaCapability;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;

import java.util.List;

public record TerrestrialModule(LevelingInt manaPerDmg) implements ModifierModule, GeneralInteractionModifierHook {

    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<TerrestrialModule>defaultHooks(ModifierHooks.GENERAL_INTERACT);
    public static final RecordLoadable<TerrestrialModule> LOADER = RecordLoadable.create(LevelingInt.LOADABLE.requiredField("manaPerDmg", TerrestrialModule::manaPerDmg), TerrestrialModule::new);

    @Override
    public @NotNull RecordLoadable<TerrestrialModule> getLoader() {
        return LOADER;
    }

    @Override
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if(hand.equals(InteractionHand.MAIN_HAND)) {
            if(source.equals(InteractionSource.LEFT_CLICK)) {
                trySpawnBurst(player, tool, manaPerDmg().computeForLevel(modifier.getEffectiveLevel()));
            }
        }
        return InteractionResult.PASS;
    }

    public void trySpawnBurst(Player player, IToolStackView tool, int mana) {
        trySpawnBurst(player, player.getAttackStrengthScale(0F), tool, mana);
    }

    public void trySpawnBurst(Player player, float attackStrength, IToolStackView tool, int mana) {
        if (!player.isSpectator()
                && !player.getMainHandItem().isEmpty()
                && attackStrength == 1 && ToolManaCapability.getMana(tool) >= mana) {
            ManaBurstEntity burst = getBurst(player, player.getMainHandItem(), mana);
            player.level().addFreshEntity(burst);
            player.getMainHandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade, SoundSource.PLAYERS, 1F, 1F);
            ToolManaCapability.setMana(tool, ToolManaCapability.getMana(tool)-mana);
        }
    }

    public ManaBurstEntity getBurst(Player player, ItemStack stack, int mana) {
        ManaBurstEntity burst = new ManaBurstEntity(player);

        float motionModifier = 7F;

        burst.setColor(0x20FF20);
        burst.setMana(mana);
        burst.setStartingMana(mana);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(4F);
        burst.setGravity(0F);
        burst.setDeltaMovement(burst.getDeltaMovement().scale(motionModifier));

        burst.setSourceLens(stack.copy());

        return burst;
    }
}
