package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class SSSchoolRegistry {
    private static final DeferredRegister<SchoolType> SCHOOLS = DeferredRegister.create(SchoolRegistry.SCHOOL_REGISTRY_KEY, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { SCHOOLS.register(eventBus); }

    public static final ResourceLocation SPECTOR_RESOURCE = SpectorSpellbooks.id("spector");

    public static final RegistryObject<SchoolType> SPECTOR =
            register(() -> new SchoolType(
                    SPECTOR_RESOURCE,
                    SSTagRegistry.SPECTOR_FOCUS,
                    Component.translatable("school.spector_spellbooks.spector").setStyle(Style.EMPTY.withColor(0xE9FCFF)),
                    SSAttributeRegistry.SPECTOR_SPELL_POWER,
                    SSAttributeRegistry.SPECTOR_MAGIC_RESIST,
                    () -> SoundEvents.EVOKER_CAST_SPELL,
                    ISSDamageTypes.FIRE_MAGIC
            ));

    private static RegistryObject<SchoolType> register(Supplier<SchoolType> schoolType) {
        return SCHOOLS.register(schoolType.get().getId().getPath(), schoolType);
    }
}
