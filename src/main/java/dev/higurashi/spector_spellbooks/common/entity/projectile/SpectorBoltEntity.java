package dev.higurashi.spector_spellbooks.common.entity.projectile;

import dev.higurashi.spector_spellbooks.registry.SSEffectRegistry;
import dev.higurashi.spector_spellbooks.registry.SSEntityRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class SpectorBoltEntity extends AbstractMagicProjectile {
    private int effectDuration = 100;

    public SpectorBoltEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public SpectorBoltEntity(Level level, LivingEntity caster, Vec3 spawnPos, float damage, int effectDuration) {
        this(SSEntityRegistry.SPECTOR_BOLT.get(), level);
        this.setOwner(caster);
        this.setPos(spawnPos);
        this.setDamage(damage);
        this.effectDuration = effectDuration;
        this.setNoGravity(true);
    }

    @Override
    public void trailParticles() {

    }

    @Override
    public void impactParticles(double x, double y, double z) {

    }

    @Override
    public float getSpeed() {
        return 1.3f;
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        super.onHitEntity(hitResult);

        if (DamageSources.applyDamage(hitResult.getEntity(), this.damage, SSSpellRegistry.SPECTOR_BOLT.get().getDamageSource(this, this.getOwner()))) {
            if (hitResult.getEntity() instanceof LivingEntity target) SSEffectRegistry.SPECTOR_BOLT.addEffect(target, this.effectDuration);
        }

        this.doImpactSound(SoundRegistry.GUIDING_BOLT_IMPACT);
        consumeEntityImpact(hitResult, true);
    }

    @Override
    public Optional<Supplier<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }
}
