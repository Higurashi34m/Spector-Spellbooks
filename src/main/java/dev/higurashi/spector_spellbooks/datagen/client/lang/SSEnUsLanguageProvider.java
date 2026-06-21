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
        // Advancement
        add("advancement.spector_spellbooks.spell_book_spector.title", "Portable Stigmatized Property");
        add("advancement.spector_spellbooks.spell_book_spector.description", "Craft The Ghostly Book");

        // Attribute
        addAttribute(SSAttributeRegistry.SPECTOR_SPELL_POWER, "Spector Spell Power");
        addAttribute(SSAttributeRegistry.SPECTOR_MAGIC_RESIST, "Spector Magic Resistance");

        // Creative Tab
        add("creative_tab.spector_spellbooks", "Spector Spellbooks");

        // Effect
        addEffect(SSEffectRegistry.SHIFT, "Shift");
        addEffect(SSEffectRegistry.SPECTOR_BOLT, "Spectral Bolt");
        addEffect(SSEffectRegistry.POLTERGEIST, "Poltergeist");
        addEffect(SSEffectRegistry.WRAITH_GRIP, "Wraith Grip");

        // Item
        addItem(SSItemRegistry.SPECTOR_ESSENCE, "Spector Essence");
        addItem(SSItemRegistry.SPECTOR_LUMP_INGOT, "Spector Lump Ingot");
        addItem(SSItemRegistry.SPECTOR_RUNE,  "Spector Rune");
        addItem(SSItemRegistry.SPECTOR_UPGRADE_ORB, "Spector Upgrade Orb");
        addItem(SSItemRegistry.SPECTOR_HELMET, "Ghost's Rag Helmet");
        addItem(SSItemRegistry.SPECTOR_CHESTPLATE, "Ghost's Rag Chestplate");
        addItem(SSItemRegistry.SPECTOR_LEGGINGS, "Ghost's Rag Leggings");
        addItem(SSItemRegistry.SPECTOR_BOOTS, "Ghost's Rag Boots");
        addItem(SSItemRegistry.SPECTOR_SCYTHE,  "Spector Scythe");
        addItem(SSItemRegistry.SPECTOR_SPELLBOOK, "The Ghostly Book");

        // School
        addSchool(SSSchoolRegistry.SPECTOR, "spector");

        // Spell
        addSpell(SSSpellRegistry.SPECTOR_ARROW, "Spector Arrow", "Hold to charge a piercing possessed arrow. This arrow can travel through blocks");
        addSpell(SSSpellRegistry.SPECTOR_BOLT, "Spector Bolt", "Fire a possessed by spector bolt that can travel through blocks. Creatures hit will glow, allowing you to lock on to them through walls");
        addSpell(SSSpellRegistry.SPECTOR_SLASH, "Spector Slash", "Slash forward to send out a piercing possessed by spector slash. This slash can travel through blocks");
        addSpell(SSSpellRegistry.SPECTOR_BREATH, "Spector Breath", "Spew forth a cone of chilling breath, damaging creatures in its reach. This breath can pass through blocks");
        addSpell(SSSpellRegistry.RAY_OF_SPECTOR, "Ray of Spector", "Fire a beam of possessed by spector, can pass through blocks");
        addSpell(SSSpellRegistry.SHIFT, "Shift", "Cast to inflict yourself with effect, an effect that makes you able travel through blocks and creative flying. When travel through block, it is impossible to see through to the ground");
        addSpell(SSSpellRegistry.POLTERGEIST, "Poltergeist", "Launches creatures within range into the air, and launches in the direction to caster look side. After that, inflict slow falling to creatures, and when they land, hurt based on the duration of the fall");

        // Death Attack
        add("death.attack.spector_spellbooks.spector_arrow", "%1$s was penetrated by %2$s's arrow");
        add("death.attack.spector_spellbooks.spector_bolt", "%1$s was led to the grave by %2$s");
        add("death.attack.spector_spellbooks.ray_of_spector", "%1$s was sent to the afterlife by %2$s's ray of spector");
        add("death.attack.spector_spellbooks.spector_slash", "%1$s's life was severed by %2$s's spector");
        add("death.attack.spector_spellbooks.spector_breath", "%1$s was possessed by %2$s's chilling breath");
        add("death.attack.spector_spellbooks.poltergeist", "%1$s was dragged around by %2$s's poltergeist");

        // Ui
        addUi("fall_damage_ratio", "%s%% fall damage ratio");
    }
}
