package dev.higurashi.spector_spellbooks.datagen.client.lang;

import dev.higurashi.spector_spellbooks.registry.*;
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

        // Effect
        addEffect(SSEffectRegistry.SHIFT, "Shift");
        addEffect(SSEffectRegistry.SPECTOR_BOLT, "Spectral Bolt");
        addEffect(SSEffectRegistry.POLTERGEIST, "Poltergeist");
        addEffect(SSEffectRegistry.WRAITH_GRIP, "Wraith Grip");

        // Item
        addItem(SSItemRegistry.SPECTOR_ESSENCE, "Spector Essence");
        addItem(SSItemRegistry.SPECTOR_LUMP_INGOT, "Spector Lump Ingot");
        addItem(SSItemRegistry.SPECTOR_RUNE,  "Spector Rune");
        addItem(SSItemRegistry.SPECTOR_HELMET, "Spector Helmet");
        addItem(SSItemRegistry.SPECTOR_CHESTPLATE, "Spector Chestplate");
        addItem(SSItemRegistry.SPECTOR_LEGGINGS, "Spector Leggings");
        addItem(SSItemRegistry.SPECTOR_BOOTS, "Spector Boots");
        addItem(SSItemRegistry.SPECTOR_SCYTHE,  "Spector Scythe");
        addItem(SSItemRegistry.SPECTOR_SPELLBOOK, "Spector Spellbook");

        // School
        addSchool(SSSchoolRegistry.SPECTOR, "spector");

        // Spell
        addSpell(SSSpellRegistry.SPECTOR_ARROW, "Spector Arrow", "");
        addSpell(SSSpellRegistry.SPECTOR_BOLT, "Spector Bolt", "");
        addSpell(SSSpellRegistry.SPECTOR_SLASH, "Spector Slash", "");
        addSpell(SSSpellRegistry.SPECTOR_BREATH, "Spector Breath", "");
        addSpell(SSSpellRegistry.RAY_OF_SPECTOR, "Ray of Spector", "");
        addSpell(SSSpellRegistry.SHIFT, "Shift", "");
        addSpell(SSSpellRegistry.POLTERGEIST, "Poltergeist", "");

        // Ui
        addUi("fall_damage_ratio", "%s%% fall damage ratio");
    }
}
