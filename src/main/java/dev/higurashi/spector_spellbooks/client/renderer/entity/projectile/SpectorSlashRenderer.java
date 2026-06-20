package dev.higurashi.spector_spellbooks.client.renderer.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.SpectorSlashEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class SpectorSlashRenderer extends EntityRenderer<SpectorSlashEntity> {
    private static final ResourceLocation TEXTURE = SpectorSpellbooks.id("textures/entity/projectile/spector_slash.png");

    public SpectorSlashRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SpectorSlashEntity entity, float yaw, float delta, PoseStack poseStack, @NotNull MultiBufferSource source, int packedLight) {
        poseStack.pushPose();

        PoseStack.Pose pose = poseStack.last();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(delta, entity.yRotO, entity.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(delta, entity.xRotO, entity.getXRot())));

        poseStack.mulPose(Axis.YP.rotationDegrees(-15));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-10));
        drawSlash(pose, entity, source, packedLight, entity.getBbWidth());

        poseStack.mulPose(Axis.YP.rotationDegrees(30));
        poseStack.mulPose(Axis.ZP.rotationDegrees(20));
        drawSlash(pose, entity, source, packedLight, entity.getBbWidth());

        poseStack.popPose();

        super.render(entity, yaw, delta, poseStack, source, packedLight);
    }

    private void drawSlash(PoseStack.Pose pose, SpectorSlashEntity entity, MultiBufferSource source, int packedLight, float width) {
        Matrix4f poseMatrix = pose.pose();

        VertexConsumer consumer = source.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        float halfWidth = width * 0.5f;
        consumer.vertex(poseMatrix, -halfWidth, -0.1f, -halfWidth).color(255, 255, 255, 150).uv(0f, 1f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, halfWidth, -0.1f, -halfWidth).color(255, 255, 255, 150).uv(1f, 1f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, halfWidth, -0.1f, halfWidth).color(255, 255, 255, 150).uv(1f, 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0f, 1f, 0f).endVertex();
        consumer.vertex(poseMatrix, -halfWidth, -0.1f, halfWidth).color(255, 255, 255, 150).uv(0f, 0f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(0f, 1f, 0f).endVertex();
    }

    @Override @NotNull
    public ResourceLocation getTextureLocation(@NotNull SpectorSlashEntity entity) {
        return TEXTURE;
    }
}