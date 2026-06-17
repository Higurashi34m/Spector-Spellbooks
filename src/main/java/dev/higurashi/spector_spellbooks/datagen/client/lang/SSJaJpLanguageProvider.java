package dev.higurashi.spector_spellbooks.datagen.client.lang;

import dev.higurashi.spector_spellbooks.registry.SSAttributeRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import net.minecraft.data.PackOutput;

import java.util.Locale;

public class SSJaJpLanguageProvider extends BaseLanguageProvider {
    public SSJaJpLanguageProvider(PackOutput output) {
        super(output, Locale.JAPAN.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        // Attribute
        addAttribute(SSAttributeRegistry.SPECTOR_SPELL_POWER, "亡霊魔法の威力");
        addAttribute(SSAttributeRegistry.SPECTOR_MAGIC_RESIST, "亡霊魔法耐性");

        // School
        addSchool(SSSchoolRegistry.SPECTOR, "亡霊");

        // Spell
        addSpell(SSSpellRegistry.SPECTOR_ARROW, "スペクターアロー", "");
        addSpell(SSSpellRegistry.SPECTOR_BOLT, "スペクターボルト", "");
        addSpell(SSSpellRegistry.POLTERGEIST, "ポルターガイスト", "");

        // Ui
        addUi("fall_damage_ratio", "落下時ダメージ倍率: %s%%");
    }
}
