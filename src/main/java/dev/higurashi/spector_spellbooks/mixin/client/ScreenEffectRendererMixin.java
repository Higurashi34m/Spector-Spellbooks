package dev.higurashi.spector_spellbooks.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.higurashi.spector_spellbooks.registry.SSEffectRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin { @Shadow private static Pair<BlockState, BlockPos> getOverlayBlock(Player player) { return null; }
    @Shadow private static void renderTex(TextureAtlasSprite texture, PoseStack poseStack) {}

    @Inject(method = "renderScreenEffect", at = @At("HEAD"))
    private static void renderScreenEffect(Minecraft minecraft, PoseStack poseStack, CallbackInfo ci) {
        ClientLevel level = minecraft.level;
        Player player = minecraft.player;

        if (level == null || player == null) return;
        if (!player.hasEffect(SSEffectRegistry.SHIFT.get())) return;

        Pair<BlockState, BlockPos> overlay = getOverlayBlock(player);
        if (overlay == null) return;
        if (!ForgeHooksClient.renderBlockOverlay(player, poseStack, RenderBlockScreenEffectEvent.OverlayType.BLOCK, overlay.getLeft(), overlay.getRight())) {
            renderTex(minecraft.getBlockRenderer().getBlockModelShaper().getTexture(overlay.getLeft(), level, overlay.getRight()), poseStack);
        }
    }
}
