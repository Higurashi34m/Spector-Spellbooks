package dev.higurashi.spector_spellbooks.common.entity.projectile;

import dev.higurashi.spector_spellbooks.registry.SSEntityRegistry;
import io.redspace.ironsspellbooks.entity.spells.ray_of_frost.RayOfFrostVisualEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RayOfSpectorEntity extends RayOfFrostVisualEntity {
    public RayOfSpectorEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    public RayOfSpectorEntity(Level level, LivingEntity caster, Vec3 start, Vec3 end) {
        super(SSEntityRegistry.RAY_OF_SPECTOR.get(), level);
        this.setPos(start.add(0, -0.75, 0));
        this.distance = (float) start.distanceTo(end);
        this.setRot(caster.getYRot(), caster.getXRot());
    }
}
