package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.daybreaklib.api.registry.DamageTypeRegistryManager;
import dev.higurashi.daybreaklib.api.registry.reference.DamageTypeRef;
import dev.higurashi.spector_spellbooks.SpectorSpellbooks;

public class SSDamageTypeRegistry {
    private static final DamageTypeRegistryManager DAMAGE_TYPES = new DamageTypeRegistryManager(SpectorSpellbooks.MOD_ID);

    public static final DamageTypeRef SPECTOR_MAGIC = DAMAGE_TYPES.create("spector_magic");
}
