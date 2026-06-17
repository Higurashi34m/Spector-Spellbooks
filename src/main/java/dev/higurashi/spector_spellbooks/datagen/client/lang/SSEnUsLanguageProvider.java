package dev.higurashi.spector_spellbooks.datagen.client.lang;

import dev.higurashi.spector_spellbooks.registry.SSAttributeRegistry;
import dev.higurashi.spector_spellbooks.registry.SSItemRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
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
        addItem(SSItemRegistry.SPECTOR_HELMET, "Spector Helmet");
        addItem(SSItemRegistry.SPECTOR_CHESTPLATE, "Spector Chestplate");
        addItem(SSItemRegistry.SPECTOR_LEGGINGS, "Spector Leggings");
        addItem(SSItemRegistry.SPECTOR_BOOTS, "Spector Boots");

        // School
        addSchool(SSSchoolRegistry.SPECTOR, "spector");

        // Spell
        addSpell(SSSpellRegistry.SPECTOR_ARROW, "Spector Arrow", "");
        addSpell(SSSpellRegistry.SPECTOR_BOLT, "Spector Bolt", "");
        addSpell(SSSpellRegistry.POLTERGEIST, "Poltergeist", "");

        // Ui
        addUi("fall_damage_ratio", "%s%% fall damage ratio");
    }
}
