package dev.higurashi.spector_spellbooks.setup;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.RayOfSpectorRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.SpectorArrowRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.SpectorBoltRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.layer.SpectorArrowLayer;
import dev.higurashi.spector_spellbooks.registry.SSEntityRegistry;
import io.redspace.ironsspellbooks.entity.spells.ray_of_frost.RayOfFrostRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectorSpellbooks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onRegisterEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SSEntityRegistry.SPECTOR_ARROW.get(), SpectorArrowRenderer::new);
        event.registerEntityRenderer(SSEntityRegistry.SPECTOR_BOLT.get(), SpectorBoltRenderer::new);
        event.registerEntityRenderer(SSEntityRegistry.RAY_OF_SPECTOR.get(),  RayOfSpectorRenderer::new);
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skinName : event.getSkins()) {
            PlayerRenderer renderer = event.getPlayerSkin(skinName);
            if (renderer == null) continue;

            renderer.addLayer(new SpectorArrowLayer<>(renderer));
        }
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RayOfFrostRenderer.MODEL_LAYER_LOCATION, RayOfSpectorRenderer::createBodyLayer);
    }
}
