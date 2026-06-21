package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import io.redspace.ironsspellbooks.item.armor.UpgradeOrbType;
import io.redspace.ironsspellbooks.registries.UpgradeOrbTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class SSUpgradeOrbRegistry {
    private static final ResourceKey<Registry<UpgradeOrbType>> UPGRADE_ORB_TYPE = UpgradeOrbTypeRegistry.UPGRADE_ORB_REGISTRY_KEY;

    public static ResourceKey<UpgradeOrbType> SPECTOR_SPELL_POWER = ResourceKey.create(UPGRADE_ORB_TYPE, SpectorSpellbooks.id("spector_power"));
}
