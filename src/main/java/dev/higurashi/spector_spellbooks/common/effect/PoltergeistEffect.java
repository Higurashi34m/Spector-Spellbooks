package dev.higurashi.spector_spellbooks.common.effect;

import dev.higurashi.daybreaklib.api.capability.effect_caster_tracker.EffectCasterTrackerManager;
import dev.higurashi.spector_spellbooks.common.spell.spector.PoltergeistSpell;
import dev.higurashi.spector_spellbooks.registry.SSEffectRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.UUID;

public class PoltergeistEffect extends MobEffect {
    public PoltergeistEffect() {
        super(MobEffectCategory.HARMFUL, 0x4A0E4E);
        this.addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "b3a1d975-e807-461a-98d1-59055658ac17", -0.9, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        MagicManager.spawnParticles(entity.level(), new DustParticleOptions(new Vector3f(0.6f, 0.6f, 0.6f), 1.0f), entity.getRandomX(1.0), entity.getRandomY(), entity.getRandomZ(1.0), 3, 0.0, 0.0, 0.0, 0.0, false);

        if (!(entity.level() instanceof ServerLevel level)) return;

        if (entity.getEffect(this).getDuration() == 1) {
            UUID casterUuid = EffectCasterTrackerManager.getCasterOf(entity, this);

            if (!(level.getEntity(casterUuid) instanceof LivingEntity caster)) return;
            entity.setDeltaMovement(entity.getDeltaMovement().add(caster.getForward().scale(1.5)));

            AbstractSpell spell = SSSpellRegistry.POLTERGEIST.get();
            DamageSources.applyDamage(entity, PoltergeistSpell.getDamage(amplifier + 1, caster), spell.getDamageSource(caster));

            MagicManager.spawnParticles(level, new BlastwaveParticleOptions(new Vector3f(0.6f, 0.6f, 0.6f), entity.getBbWidth() * 4.0f), entity.getX(), entity.getY(), entity.getZ(), 1, 0.0, 0.0, 0.0, 0.0, false);

            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200));
            SSEffectRegistry.WRAITH_GRIP.addAndTrackEffect(caster, entity, 200, amplifier);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        return modifier.getAmount();
    }
}
