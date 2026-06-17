package dev.higurashi.spector_spellbooks.common.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SpectorBoltEffect extends MagicMobEffect {
    public SpectorBoltEffect() {
        super(MobEffectCategory.HARMFUL, 0xFFFFFF);
    }
}
