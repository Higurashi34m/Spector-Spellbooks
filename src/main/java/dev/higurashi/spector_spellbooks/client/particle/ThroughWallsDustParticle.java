package dev.higurashi.spector_spellbooks.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.DustParticleOptions;
import org.jetbrains.annotations.NotNull;

public class ThroughWallsDustParticle extends DustParticle {

    public ThroughWallsDustParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpectorDustParticleOptions options, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, new DustParticleOptions(options.getColor(), options.getScale()), sprites);

        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;

        this.friction = 0.98f;
        this.hasPhysics = false;
    }

    @Override @NotNull
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SpectorDustParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(@NotNull SpectorDustParticleOptions options, @NotNull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ThroughWallsDustParticle particle = new ThroughWallsDustParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, options, this.sprites);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}