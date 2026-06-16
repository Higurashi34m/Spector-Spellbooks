package dev.higurashi.spector_spellbooks.client.renderer.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.render.RenderHelper;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SpectorArrowRenderer extends EntityRenderer<AbstractMagicProjectile> {
    private static final ResourceLocation TEXTURE = SpectorSpellbooks.id("textures/entity/projectile/spector_arrow.png");

    public SpectorArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override @NotNull
    public ResourceLocation getTextureLocation(@NotNull AbstractMagicProjectile entity) {
        return TEXTURE;
    }

    @Override
    public void render(@NotNull AbstractMagicProjectile entity, float yaw, float delta, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        Vec3 movement = entity.getDeltaMovement();
        poseStack.mulPose(Axis.YP.rotationDegrees(-((float) (Math.toDegrees(Math.atan2(movement.z, movement.x))) + 90.0f)));
        poseStack.mulPose(Axis.XP.rotationDegrees(-((float) (Math.toDegrees(Math.atan2(movement.horizontalDistance(), movement.y))) - 90.0f)));

        renderModel(poseStack, buffer);
        poseStack.popPose();
    }

    public static void renderModel(PoseStack poseStack, MultiBufferSource buffer) {
        poseStack.scale(0.175f, 0.175f, 0.175f);
        poseStack.translate(0.0, 0.0f, 5.0f);

        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();

        VertexConsumer consumer = buffer.getBuffer(RenderHelper.CustomerRenderType.magic(TEXTURE));

        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
        for (int i = 0; i < 4; i++) {
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
            vertex(matrix4f, matrix3f, consumer, -8.0f, -2.0f, 0.0f, 0.0f);
            vertex(matrix4f, matrix3f, consumer,  8.0f, -2.0f, 0.5f, 0.0f);
            vertex(matrix4f, matrix3f, consumer,  8.0f,  2.0f, 0.5f, 0.15625f);
            vertex(matrix4f, matrix3f, consumer, -8.0f,  2.0f, 0.0f, 0.15625f);
        }
    }

    private static void vertex(Matrix4f matrix, Matrix3f normal, VertexConsumer consumer, float x, float y, float u, float v) {
        consumer.vertex(matrix, x, y, 0.0f)
                .color(200, 200, 200, 200)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(LightTexture.FULL_BRIGHT)
                .normal(normal, 0.0f, 1.0f, 0.0f)
                .endVertex();
    }
}
