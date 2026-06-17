package dev.higurashi.spector_spellbooks.common.entity.projectile;

import dev.higurashi.spector_spellbooks.registry.SSEntityRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.function.Supplier;

public class SpectorArrowEntity extends AbstractMagicProjectile {
    public SpectorArrowEntity(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
        this.setInfinitePiercing();
    }

    public SpectorArrowEntity(Level level, LivingEntity caster, Vec3 spawnPos, float damage) {
        this(SSEntityRegistry.SPECTOR_ARROW.get(), level);
        this.setOwner(caster);
        this.setPos(spawnPos);
        this.setDamage(damage);
    }

    @Override
    public void trailParticles() {
        if (!this.level().isClientSide) return;
        for (int i = 0; i < 3; i++) {
            this.level().addParticle(new DustParticleOptions(new Vector3f(0.6f, 0.6f, 0.6f), 1.0f), this.getRandomX(1.0), this.getRandomY(), this.getRandomZ(1.0), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onHitEntity(@NotNull EntityHitResult result) {
        if (this.level().isClientSide) return;
        if (!(result.getEntity() instanceof LivingEntity target) || !(this.getOwner() instanceof LivingEntity caster)) return;

        DamageSources.applyDamage(target, this.getDamage(), SSSpellRegistry.SPECTOR_ARROW.get().getDamageSource(this, caster));
        this.discardHelper(result);
    }

    @Override
    public void impactParticles(double x, double y, double z) {

    }

    @Override
    public float getSpeed() {
        return 2.6f;
    }

    @Override
    public Optional<Supplier<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }
}
