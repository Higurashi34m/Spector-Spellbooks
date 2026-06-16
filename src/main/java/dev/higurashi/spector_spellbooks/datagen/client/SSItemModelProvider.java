package dev.higurashi.spector_spellbooks.datagen.client;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.registry.SSItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SSItemModelProvider extends ItemModelProvider {
    public SSItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, SpectorSpellbooks.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        basicItem(SSItemRegistry.SPECTOR_ESSENCE.get());
        basicItem(SpectorSpellbooks.id("scroll_spector"));
    }
}
