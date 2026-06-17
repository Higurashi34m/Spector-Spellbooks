package dev.higurashi.spector_spellbooks.mixin;

import dev.higurashi.spector_spellbooks.registry.SSEffectRegistry;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "isCurrentlyGlowing", at = @At(value = "HEAD"), cancellable = true)
    public void spector_spellbooks$isCurrentlyGlowing(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!self.level().isClientSide() && self.hasEffect(SSEffectRegistry.SPECTOR_BOLT.get())) {
            cir.setReturnValue(true);
        }
    }
}
