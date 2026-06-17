package dev.higurashi.spector_spellbooks.client.renderer.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.RayOfSpectorEntity;
import io.redspace.ironsspellbooks.entity.spells.ray_of_frost.RayOfFrostRenderer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class RayOfSpectorRenderer extends EntityRenderer<RayOfSpectorEntity> {
    private static final ResourceLocation INNER_TEXTURE = SpectorSpellbooks.id("textures/entity/projectile/ray_of_spector_inner.png");
    private static final ResourceLocation OUTER_TEXTURE = SpectorSpellbooks.id("textures/entity/projectile/ray_of_spector_outer.png");

    private final ModelPart body;

    public RayOfSpectorRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.body = context.bakeLayer(RayOfFrostRenderer.MODEL_LAYER_LOCATION).getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();
        part.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8, -16, -8, 16, 32, 16), PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void render(RayOfSpectorEntity entity, float yaw, float delta, PoseStack poseStack, @NotNull MultiBufferSource source, int packedLight) {
        float lifeTime = RayOfSpectorEntity.lifetime;
        float segmentLength = 32 * 0.25f * 0.25f;
        float animTime = entity.tickCount + delta;
        float alpha = Mth.clamp(1.0f - animTime / lifeTime, 0, 0.5f);

        poseStack.pushPose();

        poseStack.translate(0, entity.getBbHeight() * 0.5f, 0);
        poseStack.mulPose(Axis.YP.rotationDegrees(-entity.getYRot() - 180.0f));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getXRot() - 90.0f));
        poseStack.scale(0.25f, 0.25f, 0.25f);

        for (float i = 0; i < entity.distance * 4; i += segmentLength) {
            poseStack.translate(0, segmentLength, 0);

            renderOuterAura(poseStack, source, animTime, lifeTime, alpha);
            renderInnerCore(poseStack, source, animTime, lifeTime);
        }


        poseStack.popPose();
        super.render(entity, yaw, delta, poseStack, source, packedLight);
    }

    private void renderOuterAura(PoseStack poseStack, MultiBufferSource source, float animTime, float lifeTime, float alpha) {
        float scale = Mth.clampedLerp(1.2f, 0f, animTime / lifeTime);
        VertexConsumer consumer = source.getBuffer(RenderType.energySwirl(OUTER_TEXTURE, 0, 0));

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(animTime * 5.0f));
        poseStack.scale(scale, 1.0f, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees(45.0f));
        body.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, alpha);
        poseStack.popPose();
    }

    private void renderInnerCore(PoseStack poseStack, MultiBufferSource source, float animTime, float lifeTime) {
        float scale = Mth.clampedLerp(1f, 0f, animTime / (lifeTime - 8f));
        VertexConsumer consumer = source.getBuffer(RenderType.energySwirl(INNER_TEXTURE, 0, 0));

        poseStack.pushPose();
        poseStack.scale(scale, 1.0f, scale);
        poseStack.mulPose(Axis.YP.rotationDegrees(animTime * -10.0f));
        body.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 0.5f);
        poseStack.popPose();
    }

    @Override @NotNull
    public ResourceLocation getTextureLocation(@NotNull RayOfSpectorEntity entity) {
        return INNER_TEXTURE;
    }
}
