package dev.higurashi.spector_spellbooks.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.higurashi.spector_spellbooks.client.renderer.entity.projectile.SpectorArrowRenderer;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.SyncedSpellData;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class SpectorArrowLayer<E extends LivingEntity, M extends HumanoidModel<E>> extends RenderLayer<E, M> {
    public SpectorArrowLayer(RenderLayerParent<E, M> parent) {
        super(parent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, @NotNull E entity, float limbSwing, float swingAmount, float delta, float ageInTicks, float headYaw, float headPitch) {
        SyncedSpellData spellData = ClientMagicData.getSyncedSpellData(entity);
        if (!spellData.isCasting() || !spellData.getCastingSpellId().equals(SSSpellRegistry.SPECTOR_ARROW.get().getSpellId())) return;

        poseStack.pushPose();
        this.getParentModel().translateToHand(HumanoidArm.RIGHT, poseStack);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
        poseStack.scale(0.75f, 0.75f, 0.75f);
        poseStack.translate(0.15, 0.15, -1.25);
        SpectorArrowRenderer.renderModel(poseStack, buffer);
        poseStack.popPose();
    }

    public static class Geo extends GeoRenderLayer<AbstractSpellCastingMob> {
        public Geo(GeoRenderer<AbstractSpellCastingMob> renderer) {
            super(renderer);
        }

        @Override
        public void renderForBone(PoseStack poseStack, AbstractSpellCastingMob entity, GeoBone bone, RenderType renderType, MultiBufferSource buffer, VertexConsumer consumer, float partialTick, int packedLight, int packedOverlay) {
            SyncedSpellData spellData = ClientMagicData.getSyncedSpellData(entity);
            if (!spellData.isCasting() || !spellData.getCastingSpellId().equals(SSSpellRegistry.SPECTOR_ARROW.get().getSpellId())) return;

            if (bone.getName().equals("right_arm")) {
                poseStack.pushPose();
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));
                poseStack.scale(0.75f, 0.75f, 0.75f);
                poseStack.translate(-0.15, -0.15, 1.15);
                SpectorArrowRenderer.renderModel(poseStack, buffer);
                poseStack.popPose();
            }
        }
    }
}
