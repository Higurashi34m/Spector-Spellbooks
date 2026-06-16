package dev.higurashi.spector_spellbooks.datagen.client.lang;

import dev.higurashi.spector_spellbooks.registry.SSAttributeRegistry;
import dev.higurashi.spector_spellbooks.registry.SSItemRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import net.minecraft.data.PackOutput;

import java.util.Locale;

public class SSEnUsLanguageProvider extends BaseLanguageProvider {
    public SSEnUsLanguageProvider(PackOutput output) {
        super(output, Locale.US.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        // Attribute
        addAttribute(SSAttributeRegistry.SPECTOR_SPELL_POWER, "Spector Spell Power");
        addAttribute(SSAttributeRegistry.SPECTOR_MAGIC_RESIST, "Spector Magic Resistance");

        // Item
        addItem(SSItemRegistry.SPECTOR_ESSENCE, "Spector Essence");

        // School
        addSchool(SSSchoolRegistry.SPECTOR, "spector");
    }
}
