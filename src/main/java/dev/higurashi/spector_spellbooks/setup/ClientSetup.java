package dev.higurashi.spector_spellbooks.setup;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.SpectorArrowRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.layer.SpectorArrowLayer;
import dev.higurashi.spector_spellbooks.registry.SSEntityRegistry;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectorSpellbooks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void onRegisterEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SSEntityRegistry.SPECTOR_ARROW.get(), SpectorArrowRenderer::new);
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skinName : event.getSkins()) {
            PlayerRenderer renderer = event.getPlayerSkin(skinName);
            if (renderer == null) continue;

            renderer.addLayer(new SpectorArrowLayer<>(renderer));
        }
    }
}
