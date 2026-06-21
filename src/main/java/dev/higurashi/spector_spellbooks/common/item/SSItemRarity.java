package dev.higurashi.spector_spellbooks.common.item;

import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import net.minecraft.world.item.Rarity;

public class SSItemRarity {
    public static final Rarity SPECTOR = Rarity.create("spector", style -> SSSchoolRegistry.SPECTOR.get().getDisplayName().getStyle());
}
