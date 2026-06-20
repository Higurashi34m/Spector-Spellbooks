package dev.higurashi.spector_spellbooks.client.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.higurashi.spector_spellbooks.registry.SSParticleRegistry;
import net.minecraft.core.particles.DustParticleOptionsBase;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class SpectorDustParticleOptions extends DustParticleOptionsBase {

    public static final Codec<SpectorDustParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(options -> options.color),
            Codec.FLOAT.fieldOf("scale").forGetter(options -> options.scale)
    ).apply(instance, SpectorDustParticleOptions::new));


    @SuppressWarnings("deprecation")
    public static final ParticleOptions.Deserializer<SpectorDustParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override @NotNull
        public SpectorDustParticleOptions fromCommand(@NotNull ParticleType<SpectorDustParticleOptions> type, @NotNull StringReader reader) throws CommandSyntaxException {
            Vector3f vector3f = DustParticleOptionsBase.readVector3f(reader);
            reader.expect(' ');
            float f = reader.readFloat();
            return new SpectorDustParticleOptions(vector3f, f);
        }

        @Override @NotNull
        public SpectorDustParticleOptions fromNetwork(@NotNull ParticleType<SpectorDustParticleOptions> type, @NotNull FriendlyByteBuf buffer) {
            return new SpectorDustParticleOptions(DustParticleOptionsBase.readVector3f(buffer), buffer.readFloat());
        }
    };

    public SpectorDustParticleOptions(Vector3f color, float scale) {
        super(color, scale);
    }

    @Override @NotNull
    public ParticleType<SpectorDustParticleOptions> getType() {
        return SSParticleRegistry.THROUGH_WALL_DUST.get();
    }
}