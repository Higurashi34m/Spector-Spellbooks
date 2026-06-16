package dev.higurashi.spector_spellbooks;

import dev.higurashi.spector_spellbooks.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SpectorSpellbooks.MOD_ID)
public class SpectorSpellbooks {
    public static final String MOD_ID = "spector_spellbooks";

    public SpectorSpellbooks() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SSAttributeRegistry.register(eventBus);
        SSEntityRegistry.register(eventBus);
        SSItemRegistry.register(eventBus);
        SSSchoolRegistry.register(eventBus);
        SSSpellRegistry.register(eventBus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
