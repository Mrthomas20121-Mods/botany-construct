package mrthomas20121.botanyconstruct.module;

import mrthomas20121.botanyconstruct.BotanyConstruct;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.CapacityStat;
import slimeknights.tconstruct.library.tools.stat.ToolStatId;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.mana.ManaDiscountArmor;
import vazkii.botania.api.mana.ManaItem;

import java.util.function.Supplier;

public record ToolManaCapability(Supplier<? extends IToolStackView> tool) implements ManaItem, ManaDiscountArmor {

    public static final String MANA_FORMAT = BotanyConstruct.makeDescriptionId("tool_stat", "manaReduction");

    public static final ResourceLocation MANA_KEY = BotanyConstruct.getResource("mana");

    public static final ResourceLocation MANA_REDUCTION_KEY = BotanyConstruct.getResource("mana_reduction");

    public static final CapacityStat MAX_STAT = ToolStats.register(new CapacityStat(new ToolStatId(BotanyConstruct.MOD_ID, "max_mana"), 0xa00000, MANA_FORMAT));

    /** Gets the max manaReduction on a tool */
    public static int getMaxMana(IToolStackView tool) {
        return tool.getStats().getInt(MAX_STAT);
    }

    /** Gets the current manaReduction on a tool */
    public static int getMana(IToolStackView tool) {
        return tool.getPersistentData().getInt(MANA_KEY);
    }

    private static void setManaRaw(IToolStackView tool, int mana) {
        if (mana == 0) {
            tool.getPersistentData().remove(MANA_KEY);
        } else {
            tool.getPersistentData().putInt(MANA_KEY, mana);
        }
    }

    public static void setMana(IToolStackView tool, int mana) {
        setManaRaw(tool, Mth.clamp(mana, 0, getMaxMana(tool)));
    }

    public static void addMana(IToolStackView tool, int mana) {
        if(mana != 0) {
            setMana(tool, getMana(tool)+mana);
        }
    }

    /** Ensures the tool's manaReduction is within the cap. */
    public static void checkMana(IToolStackView tool) {
        int mana = getMana(tool);
        if (mana < 0) {
            setManaRaw(tool, 0);
        } else {
            int capacity = getMaxMana(tool);
            if (mana > capacity) {
                setManaRaw(tool, capacity);
            }
        }
    }

    @Override
    public int getMana() {
        return getMana(tool.get());
    }

    @Override
    public int getMaxMana() {
        return getMaxMana(tool.get());
    }

    @Override
    public void addMana(int mana) {
        setManaRaw(tool.get(), mana);
    }

    @Override
    public boolean canReceiveManaFromPool(BlockEntity pool) {
        return true;
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack otherStack) {
        return true;
    }

    @Override
    public boolean canExportManaToPool(BlockEntity pool) {
        return false;
    }

    @Override
    public boolean canExportManaToItem(ItemStack otherStack) {
        return false;
    }

    @Override
    public boolean isNoExport() {
        return false;
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, Player player, @Nullable ItemStack t) {
        IToolStackView toolStack = tool.get();
        return toolStack.getPersistentData().contains(MANA_REDUCTION_KEY) ? toolStack.getPersistentData().getFloat(MANA_REDUCTION_KEY): 0;
    }

    public static class Provider implements ToolCapabilityProvider.IToolCapabilityProvider {
        private final LazyOptional<ToolManaCapability> manaItem;
        public Provider(Supplier<? extends IToolStackView> toolStack) {
            this.manaItem = LazyOptional.of(() -> new ToolManaCapability(toolStack));
        }

        @Override
        public <T> @NotNull LazyOptional<T> getCapability(IToolStackView tool, Capability<T> cap) {
            if (cap == BotaniaForgeCapabilities.MANA_ITEM && tool.getStats().getInt(MAX_STAT) > 0) {
                return manaItem.cast();
            }
            return LazyOptional.empty();
        }
    }
}
