package dev.higurashi.spector_spellbooks.common.item.handler;

import dev.higurashi.spector_spellbooks.common.item.weapon.SpectorScytheItem;
import dev.higurashi.spector_spellbooks.common.spell.spector.SpectorSlashSpell;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpectorScytheEvent {
    @SubscribeEvent
    public static void onSpellLevelManage(ModifySpellLevelEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity == null) return;

        if (entity.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof SpectorScytheItem) {
            if (event.getSpell() instanceof SpectorSlashSpell) {
                event.setLevel(event.getLevel() + 1);
            }
        }
    }
}
