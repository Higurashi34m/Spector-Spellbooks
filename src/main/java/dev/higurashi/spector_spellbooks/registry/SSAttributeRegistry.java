package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import io.redspace.ironsspellbooks.api.attribute.MagicPercentAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SSAttributeRegistry {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { ATTRIBUTES.register(eventBus); }

    public static final RegistryObject<Attribute> SPECTOR_SPELL_POWER = ATTRIBUTES.register("spector_spell_power",
            () -> new MagicPercentAttribute("attribute.spector_spellbooks.spector_spell_power", 1.0, -100.0, 100.0));

    public static final RegistryObject<Attribute> SPECTOR_MAGIC_RESIST = ATTRIBUTES.register("spector_magic_resist",
            () -> new MagicPercentAttribute("attribute.spector_spellbooks.spector_magic_resist", 1.0, -100.0, 100.0));
}
