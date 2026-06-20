package dev.higurashi.spector_spellbooks.common.entity.projectile;

import dev.higurashi.spector_spellbooks.client.particle.SpectorDustParticleOptions;
import dev.higurashi.spector_spellbooks.registry.SSEntityRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpectorBreathEntity extends AbstractConeProjectile {
    public SpectorBreathEntity(EntityType<? extends AbstractConeProjectile> type, Level level) {
        super(type, level);
    }

    public SpectorBreathEntity(Level level, LivingEntity entity, Vec3 spawnPos, float damage) {
        super(SSEntityRegistry.SPECTOR_BREATH.get(), level, entity);
        this.setPos(spawnPos);
        this.setDamage(damage);
    }

    @Override
    public void spawnParticles() {
        var owner = getOwner();
        if (!this.level().isClientSide || owner == null) return;

        Vec3 rotation = owner.getLookAngle().normalize();
        var pos = owner.position().add(rotation.scale(1.6));

        double x = pos.x;
        double y = pos.y + owner.getEyeHeight() * 0.9f;
        double z = pos.z;

        double speed = random.nextDouble() * 0.35 + 0.35;
        for (int i = 0; i < 20; i++) {
            double offset = 0.15;
            double offsetX = Math.random() * 2 * offset - offset;
            double offsetY = Math.random() * 2 * offset - offset;
            double offsetZ = Math.random() * 2 * offset - offset;

            double angularness = 0.5;
            Vec3 randomVec = new Vec3(Math.random() * 2 * angularness - angularness, Math.random() * 2 * angularness - angularness, Math.random() * 2 * angularness - angularness).normalize();
            Vec3 result = (rotation.scale(3).add(randomVec)).normalize().scale(speed);
            this.level().addParticle(new SpectorDustParticleOptions(new Vector3f(0.6f, 0.6f, 0.6f), 1.0f), x + offsetX, y + offsetY, z + offsetZ, result.x, result.y, result.z);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        var entity = entityHitResult.getEntity();
        DamageSources.applyDamage(entity, damage, SSSpellRegistry.SPECTOR_BREATH.get().getDamageSource(this, getOwner()));
    }

    @Override
    protected Set<Entity> getSubEntityCollisions() {
        List<Entity> collisions = new ArrayList<>();
        for (Entity cone : subEntities) collisions.addAll(level().getEntities(cone, cone.getBoundingBox()));

        return collisions.stream().filter(target -> target != getOwner() && target instanceof LivingEntity).collect(Collectors.toSet());
    }
}
