package dev.higurashi.spector_spellbooks.setup;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.client.particle.ThroughWallsDustParticle;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.RayOfSpectorRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.SpectorArrowRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.SpectorBoltRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.SpectorSlashRenderer;
import dev.higurashi.spector_spellbooks.client.renderer.layer.SpectorArrowLayer;
import dev.higurashi.spector_spellbooks.registry.SSEntityRegistry;
import dev.higurashi.spector_spellbooks.registry.SSItemRegistry;
import dev.higurashi.spector_spellbooks.registry.SSParticleRegistry;
import io.redspace.ironsspellbooks.entity.spells.ray_of_frost.RayOfFrostRenderer;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = SpectorSpellbooks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
       CuriosRendererRegistry.register(SSItemRegistry.SPECTOR_SPELLBOOK.get(), SpellBookCurioRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SSEntityRegistry.SPECTOR_ARROW.get(), SpectorArrowRenderer::new);
        event.registerEntityRenderer(SSEntityRegistry.SPECTOR_BOLT.get(), SpectorBoltRenderer::new);
        event.registerEntityRenderer(SSEntityRegistry.SPECTOR_SLASH.get(), SpectorSlashRenderer::new);
        event.registerEntityRenderer(SSEntityRegistry.RAY_OF_SPECTOR.get(),  RayOfSpectorRenderer::new);
        event.registerEntityRenderer(SSEntityRegistry.SPECTOR_BREATH.get(), NoopRenderer::new);
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

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SSParticleRegistry.THROUGH_WALL_DUST.get(), ThroughWallsDustParticle.Provider::new);
    }
}
