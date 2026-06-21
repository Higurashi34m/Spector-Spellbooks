package dev.higurashi.spector_spellbooks.datagen.server;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.registry.SSItemRegistry;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SSRecipeProvider extends RecipeProvider {
    public SSRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SSItemRegistry.SPECTOR_ESSENCE.get())
                .pattern("TTT")
                .pattern("TES")
                .pattern("SSS")
                .unlockedBy("has_essence", has(ItemRegistry.ARCANE_ESSENCE.get()))
                .define('T', Items.GHAST_TEAR)
                .define('E', ItemRegistry.ARCANE_ESSENCE.get())
                .define('S', Items.SOUL_SAND)
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SSItemRegistry.SPECTOR_LUMP_INGOT.get())
                .pattern("EEE")
                .pattern("EME")
                .pattern("EEE")
                .unlockedBy("has_mithril", has(ItemRegistry.MITHRIL_INGOT.get()))
                .define('E', SSItemRegistry.SPECTOR_ESSENCE.get())
                .define('M', ItemRegistry.MITHRIL_INGOT.get())
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SSItemRegistry.SPECTOR_SPELLBOOK.get())
                .pattern(" EC")
                .pattern("SBQ")
                .pattern("CI ")
                .unlockedBy("has_spellbook", has(ItemRegistry.ROTTEN_SPELL_BOOK.get()))
                .define('E', SSItemRegistry.SPECTOR_ESSENCE.get())
                .define('C', ItemRegistry.MAGIC_CLOTH.get())
                .define('S', Items.SOUL_SOIL)
                .define('B', ItemRegistry.ROTTEN_SPELL_BOOK.get())
                .define('Q', Items.QUARTZ)
                .define('I', Items.CHAIN)
                .save(writer);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ItemRegistry.TIMELESS_SLURRY.get()), Ingredient.of(ItemRegistry.DECREPIT_SCYTHE.get()), Ingredient.of(SSItemRegistry.SPECTOR_LUMP_INGOT.get()), RecipeCategory.COMBAT, SSItemRegistry.SPECTOR_SCYTHE.get())
                .unlocks("has_scythe", has(ItemRegistry.DECREPIT_SCYTHE.get()))
                .save(writer, SpectorSpellbooks.id("spector_scythe_upgrade"));
    }
}