package dev.higurashi.spector_spellbooks.datagen.client.lang;

import dev.higurashi.spector_spellbooks.registry.*;
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

        // Effect
        addEffect(SSEffectRegistry.SHIFT, "シフト");
        addEffect(SSEffectRegistry.SPECTOR_BOLT, "スペクターボルト");
        addEffect(SSEffectRegistry.POLTERGEIST, "ポルターガイスト");
        addEffect(SSEffectRegistry.WRAITH_GRIP, "亡霊の掴み");

        // Item
        addItem(SSItemRegistry.SPECTOR_SPELLBOOK, "亡霊の古書");
        addItem(SSItemRegistry.SPECTOR_ESSENCE, "亡霊の怨嗟");
        addItem(SSItemRegistry.SPECTOR_LUMP_INGOT, "怨蹉の魂塊");
        addItem(SSItemRegistry.SPECTOR_SCYTHE, "亡霊の大鎌");

        // School
        addSchool(SSSchoolRegistry.SPECTOR, "亡霊");

        // Spell
        addSpell(SSSpellRegistry.SPECTOR_ARROW, "スペクターアロー", "");
        addSpell(SSSpellRegistry.SPECTOR_BOLT, "スペクターボルト", "");
        addSpell(SSSpellRegistry.RAY_OF_SPECTOR, "スペクターレイ", "");
        addSpell(SSSpellRegistry.SPECTOR_SLASH, "スペクタースラッシュ", "");
        addSpell(SSSpellRegistry.SPECTOR_BREATH, "スペクターブレス", "");
        addSpell(SSSpellRegistry.SHIFT, "シフト", "");
        addSpell(SSSpellRegistry.POLTERGEIST, "ポルターガイスト", "");

        // Ui
        addUi("fall_damage_ratio", "落下時ダメージ倍率: %s%%");
    }
}
