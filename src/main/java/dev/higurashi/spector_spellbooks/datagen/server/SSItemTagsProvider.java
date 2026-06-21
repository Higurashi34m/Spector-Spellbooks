package dev.higurashi.spector_spellbooks.datagen.server;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.registry.SSItemRegistry;
import dev.higurashi.spector_spellbooks.registry.SSTagRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SSItemTagsProvider extends ItemTagsProvider {
    public SSItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
        super(output, provider, blockTags, SpectorSpellbooks.MOD_ID, helper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        this.tag(SSTagRegistry.SPECTOR_FOCUS).add(SSItemRegistry.SPECTOR_ESSENCE.get());
        this.tag(ModTags.SCHOOL_FOCUS).addTag(SSTagRegistry.SPECTOR_FOCUS);
        this.tag(ModTags.SPELLBOOK_CURIO).add(SSItemRegistry.SPECTOR_SPELLBOOK.get());
        this.tag(Tags.Items.INGOTS).add(SSItemRegistry.SPECTOR_LUMP_INGOT.get());
    }
}
