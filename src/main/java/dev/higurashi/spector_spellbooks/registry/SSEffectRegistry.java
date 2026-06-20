package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.daybreaklib.api.annotation.AutoRegister;
import dev.higurashi.daybreaklib.api.registry.EffectRegistryManager;
import dev.higurashi.daybreaklib.api.registry.reference.EffectReference;
import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.effect.PoltergeistEffect;
import dev.higurashi.spector_spellbooks.common.effect.ShiftEffect;
import dev.higurashi.spector_spellbooks.common.effect.SpectorBoltEffect;
import dev.higurashi.spector_spellbooks.common.effect.WraithGripEffect;

@AutoRegister
public class SSEffectRegistry {
    private static final EffectRegistryManager EFFECTS = new EffectRegistryManager(SpectorSpellbooks.MOD_ID);

    public static final EffectReference POLTERGEIST = EFFECTS.create("poltergeist", PoltergeistEffect::new);
    public static final EffectReference WRAITH_GRIP = EFFECTS.create("wraith_grip", WraithGripEffect::new);
    public static final EffectReference SPECTOR_BOLT = EFFECTS.create("spector_bolt", SpectorBoltEffect::new);
    public static final EffectReference SHIFT = EFFECTS.create("shift", ShiftEffect::new);
}
