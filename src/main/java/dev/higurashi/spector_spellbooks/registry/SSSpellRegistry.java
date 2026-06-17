package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.spell.spector.PoltergeistSpell;
import dev.higurashi.spector_spellbooks.common.spell.spector.SpectorArrowSpell;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SSSpellRegistry {
    private static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { SPELLS.register(eventBus); }

    // Spector
    public static final RegistryObject<AbstractSpell> SPECTOR_ARROW = register(new SpectorArrowSpell());
    public static final RegistryObject<AbstractSpell> POLTERGEIST = register(new PoltergeistSpell());

    private static RegistryObject<AbstractSpell> register(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellResource().getPath(), () -> spell);
    }
}
