package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class SSDamageTypeRegistry {
    public static final ResourceKey<DamageType> SPECTOR_MAGIC = register("spector_magic");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, SpectorSpellbooks.id(name));
    }
}
