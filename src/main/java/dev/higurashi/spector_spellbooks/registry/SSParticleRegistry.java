package dev.higurashi.spector_spellbooks.registry;

import com.mojang.serialization.Codec;
import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.client.particle.SpectorDustParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class SSParticleRegistry {
    private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { PARTICLES.register(eventBus); }

    public static final RegistryObject<ParticleType<SpectorDustParticleOptions>> THROUGH_WALL_DUST = PARTICLES.register("through_wall_dust", () -> new ParticleType<>(false, SpectorDustParticleOptions.DESERIALIZER) {
        @Override @NotNull
        public Codec<SpectorDustParticleOptions> codec() {
            return SpectorDustParticleOptions.CODEC;
        }
    });
}
