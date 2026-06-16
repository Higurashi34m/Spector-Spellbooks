package dev.higurashi.spector_spellbooks.datagen;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.datagen.client.SSItemModelProvider;
import dev.higurashi.spector_spellbooks.datagen.client.lang.SSEnUsLanguageProvider;
import dev.higurashi.spector_spellbooks.datagen.client.lang.SSJaJpLanguageProvider;
import dev.higurashi.spector_spellbooks.datagen.server.SSBlockTagsProvider;
import dev.higurashi.spector_spellbooks.datagen.server.SSDamageTypeProvider;
import dev.higurashi.spector_spellbooks.datagen.server.SSItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = SpectorSpellbooks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SSDataGenerator {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        // Client
        generator.addProvider(event.includeClient(), new SSItemModelProvider(output, helper));
        generator.addProvider(event.includeClient(), new SSEnUsLanguageProvider(output));
        generator.addProvider(event.includeClient(), new SSJaJpLanguageProvider(output));

        // Server
        BlockTagsProvider blockTagsProvider = new SSBlockTagsProvider(output, provider, helper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new SSItemTagsProvider(output, provider, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new SSDamageTypeProvider(output));
    }
}
