package dev.higurashi.spector_spellbooks.mixin;

import dev.higurashi.spector_spellbooks.registry.SSEffectRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.RaycastBuilder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(value = Utils.class, remap = false)
public class WallLockOnMixin {
    @Unique private static final ThreadLocal<LivingEntity> spector_spellbooks$CASTER = new ThreadLocal<>();
    @Unique private static final ThreadLocal<int[]> spector_spellbooks$RANGE = new ThreadLocal<>();
    @Unique private static final ThreadLocal<float[]> spector_spellbooks$AIM_ASSIST = new ThreadLocal<>();

    @Inject(method = "preCastTargetHelper(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lio/redspace/ironsspellbooks/api/magic/MagicData;Lio/redspace/ironsspellbooks/api/spells/AbstractSpell;IFZLjava/util/function/Predicate;)Z", at = @At("HEAD"))
    private static void captureArgs(Level level, LivingEntity caster, MagicData magicData, AbstractSpell spell, int range, float aimAssist, boolean sendFailureMessage, Predicate<LivingEntity> filter, CallbackInfoReturnable<Boolean> cir) {
        spector_spellbooks$CASTER.set(caster);
        spector_spellbooks$RANGE.set(new int[]{range});
        spector_spellbooks$AIM_ASSIST.set(new float[]{aimAssist});
    }

    @ModifyVariable(method = "preCastTargetHelper(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lio/redspace/ironsspellbooks/api/magic/MagicData;Lio/redspace/ironsspellbooks/api/spells/AbstractSpell;IFZLjava/util/function/Predicate;)Z", at = @At(value = "INVOKE_ASSIGN", target = "Lio/redspace/ironsspellbooks/api/util/RaycastBuilder;build()Lnet/minecraft/world/phys/HitResult;"), name = "target")
    private static HitResult redirectBuild(HitResult result) {
        if (result instanceof EntityHitResult) return result;

        LivingEntity caster = spector_spellbooks$CASTER.get();
        if (caster == null) return result;

        int range = spector_spellbooks$RANGE.get()[0];
        float aimAssist = spector_spellbooks$AIM_ASSIST.get()[0];

        HitResult wallResult = RaycastBuilder.begin(caster.level(), caster)
                .range(range)
                .checkForBlocks(false)
                .bbInflation(aimAssist)
                .build();

        if (wallResult instanceof EntityHitResult hit && hit.getEntity() instanceof LivingEntity target && target.hasEffect(SSEffectRegistry.SPECTOR_BOLT.get())) {
            return wallResult;
        }

        return result;
    }
}
