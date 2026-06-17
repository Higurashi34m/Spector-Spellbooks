package dev.higurashi.spector_spellbooks.client.event;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.client.renderer.layer.SpectorArrowLayer;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.event.GeoRenderEvent;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@Mod.EventBusSubscriber(modid = SpectorSpellbooks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AddGeoLayerEvent {
    @SubscribeEvent
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void onGeoEntityRender(GeoRenderEvent.Entity event) {
        if (!(event.getEntity() instanceof AbstractSpellCastingMob)) return;

        GeoEntityRenderer renderer = event.getRenderer();

        if (notLayer(renderer, SpectorArrowLayer.Geo.class)) {
            renderer.addRenderLayer(new SpectorArrowLayer.Geo(renderer));
        }
    }

    private static boolean notLayer(GeoEntityRenderer<?> renderer, Class<? extends GeoRenderLayer<?>> layerClass) {
        for (GeoRenderLayer<?> layer : renderer.getRenderLayers()) {
            if (layer.getClass() == layerClass) return false;
        }
        return true;
    }
}
