package dev.higurashi.spector_spellbooks.client.renderer.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.SpectorBoltEntity;
import io.redspace.ironsspellbooks.entity.spells.guiding_bolt.GuidingBoltRenderer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class SpectorBoltRenderer extends EntityRenderer<SpectorBoltEntity> {
    private static final ResourceLocation TEXTURE = SpectorSpellbooks.id("textures/entity/projectile/spector_bolt.png");

    protected final ModelPart body;

    public SpectorBoltRenderer(EntityRendererProvider.Context context) {
        super(context);
        ModelPart modelpart = context.bakeLayer(GuidingBoltRenderer.MODEL_LAYER_LOCATION);
        this.body = modelpart.getChild("body");
    }

    @Override
    public void render(SpectorBoltEntity entity, float yaw, float delta, PoseStack poseStack, MultiBufferSource source, int packedLight) {
        poseStack.pushPose();

        Vec3 movement = entity.getDeltaMovement();
        float xRot = (float) ((Math.toDegrees(Math.atan2(movement.horizontalDistance(), movement.y))) - 90.0f);
        float yRot = (float) (-(Math.toDegrees(Math.atan2(movement.z, movement.x))) + 90.0f);

        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        VertexConsumer consumer = source.getBuffer(RenderType.energySwirl(getTextureLocation(entity), 0, 0));
        this.body.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();

        super.render(entity, yaw, delta, poseStack, source, packedLight);
    }

    @Override @NotNull
    public ResourceLocation getTextureLocation(@NotNull SpectorBoltEntity entity) {
        return TEXTURE;
    }
}
