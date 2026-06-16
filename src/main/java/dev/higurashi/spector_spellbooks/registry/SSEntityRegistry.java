package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.SpectorArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SSEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { ENTITIES.register(eventBus); }

    public static final RegistryObject<EntityType<SpectorArrowEntity>> SPECTOR_ARROW = ENTITIES.register("spector_arrow", () -> EntityType.Builder.<SpectorArrowEntity>of(SpectorArrowEntity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .fireImmune()
            .build(SpectorSpellbooks.id("spector_arrow").toString()));
}
