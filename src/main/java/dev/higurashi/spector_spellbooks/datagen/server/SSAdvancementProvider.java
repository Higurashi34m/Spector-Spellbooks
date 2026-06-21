package dev.higurashi.spector_spellbooks.datagen.server;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.registry.SSItemRegistry;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SSAdvancementProvider extends ForgeAdvancementProvider {
    public SSAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, helper, List.of(new Generator()));
    }

    public static class Generator implements AdvancementGenerator {
        @Override
        public void generate(@NotNull HolderLookup.Provider provider, @NotNull Consumer<Advancement> saver, @NotNull ExistingFileHelper helper) {
            Advancement spectorSpellbook = Advancement.Builder.advancement()
                    .parent(IronsSpellbooks.id("irons_spellbooks/spell_book_rotten"))
                    .display(SSItemRegistry.SPECTOR_SPELLBOOK.get(), Component.translatable("advancement.spector_spellbooks.spell_book_spector.title"), Component.translatable("advancement.spector_spellbooks.spell_book_spector.description"), null, FrameType.TASK, true, true, false)
                    .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(SSItemRegistry.SPECTOR_SPELLBOOK.get()))
                    .save(saver, SpectorSpellbooks.id("irons_spellbooks/spell_book_spector"), helper);
        }
    }
}
