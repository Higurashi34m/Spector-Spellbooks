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
        basicItem(SSItemRegistry.SPECTOR_LUMP_INGOT.get());
        basicItem(SSItemRegistry.SPECTOR_RUNE.get());
        basicItem(SSItemRegistry.SPECTOR_UPGRADE_ORB.get());
        basicItem(SpectorSpellbooks.id("scroll_spector"));
        basicItem(SpectorSpellbooks.id("affinity_ring_spector"));

        basicItem(SSItemRegistry.SPECTOR_HELMET.get());
        basicItem(SSItemRegistry.SPECTOR_CHESTPLATE.get());
        basicItem(SSItemRegistry.SPECTOR_LEGGINGS.get());
        basicItem(SSItemRegistry.SPECTOR_BOOTS.get());
    }
}
