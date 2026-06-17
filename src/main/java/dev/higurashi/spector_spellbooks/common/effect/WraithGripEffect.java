package dev.higurashi.spector_spellbooks.common.effect;

import dev.higurashi.daybreaklib.api.capability.effect_caster_tracker.EffectCasterTrackerManager;
import dev.higurashi.spector_spellbooks.common.spell.spector.PoltergeistSpell;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector3f;

import java.util.UUID;

public class WraithGripEffect extends MobEffect {
    public WraithGripEffect() {
        super(MobEffectCategory.HARMFUL, 0x4A0E4E);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity.level() instanceof ServerLevel level)) return;

        UUID casterUuid = EffectCasterTrackerManager.getCasterOf(entity, this);
        if (!(level.getEntity(casterUuid) instanceof LivingEntity caster)) return;

        MobEffectInstance effectInstance = entity.getEffect(this);
        if (effectInstance == null) return;

        int elapsedTicks = 200 - effectInstance.getDuration();

        if (elapsedTicks > 5 && entity.onGround()) {
            float elapsedSeconds = elapsedTicks / 20.0f;
            float finalDamage = (elapsedSeconds * 2) * PoltergeistSpell.getDamageRatio(amplifier + 1, caster);

            DamageSources.applyDamage(entity, finalDamage, SSSpellRegistry.POLTERGEIST.get().getDamageSource(caster));
            entity.removeEffect(this);
            entity.removeEffect(MobEffects.SLOW_FALLING);

            MagicManager.spawnParticles(level, new BlastwaveParticleOptions(new Vector3f(0.6f, 0.6f, 0.6f), entity.getBbWidth() * 4.0f), entity.getX(), entity.getY(), entity.getZ(), 1, 0.0, 0.0, 0.0, 0.0, false);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
