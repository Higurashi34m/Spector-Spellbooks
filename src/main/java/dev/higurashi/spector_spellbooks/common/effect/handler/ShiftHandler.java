package dev.higurashi.spector_spellbooks.common.effect.handler;

import dev.higurashi.spector_spellbooks.registry.SSEffectRegistry;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShiftHandler {
    @SubscribeEvent
    public static void onLivingHurt(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        if (!(entity.hasEffect(SSEffectRegistry.SHIFT.get()))) return;

        if (event.getSource().is(DamageTypes.IN_WALL)) event.setCanceled(true);
    }
}
