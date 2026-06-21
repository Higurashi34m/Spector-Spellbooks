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
        // Advancement
        add("advancement.spector_spellbooks.spell_book_spector.title", "携帯型事故物件");
        add("advancement.spector_spellbooks.spell_book_spector.description", "亡霊の古書を入手する");

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
        addItem(SSItemRegistry.SPECTOR_RUNE, "亡霊のルーン");
        addItem(SSItemRegistry.SPECTOR_LUMP_INGOT, "怨蹉の魂塊");
        addItem(SSItemRegistry.SPECTOR_SCYTHE, "亡霊の大鎌");
        addItem(SSItemRegistry.SPECTOR_UPGRADE_ORB, "亡霊のアップグレードオーブ");
        addItem(SSItemRegistry.SPECTOR_HELMET, "亡霊のぼろきれのヘルメット");
        addItem(SSItemRegistry.SPECTOR_CHESTPLATE, "亡霊のぼろきれのチェストプレート");
        addItem(SSItemRegistry.SPECTOR_LEGGINGS, "亡霊のぼろきれのレギンス");
        addItem(SSItemRegistry.SPECTOR_BOOTS, "亡霊のぼろきれのブーツ");

        // School
        addSchool(SSSchoolRegistry.SPECTOR, "亡霊");

        // Spell
        addSpell(SSSpellRegistry.SPECTOR_ARROW, "スペクターアロー", "壁や敵を貫通する亡霊の矢を放つ。重力の影響は受ける");
        addSpell(SSSpellRegistry.SPECTOR_BOLT, "スペクターボルト", "壁を貫通する亡霊が憑いた弾を発射する。命中したクリーチャーは一定時間発光し、壁越しでもロックオンできるようになる");
        addSpell(SSSpellRegistry.RAY_OF_SPECTOR, "スペクターレイ", "壁を貫通する亡霊が憑いたビームを発射する");
        addSpell(SSSpellRegistry.SPECTOR_SLASH, "スペクタースラッシュ", "前方に斬りつけて壁や敵を貫通する亡霊が憑いた斬撃を放つ");
        addSpell(SSSpellRegistry.SPECTOR_BREATH, "スペクターブレス", "継続的に背筋の凍る息を前方に放つ。この息は壁を貫通する");
        addSpell(SSSpellRegistry.SHIFT, "シフト", "唱えると、一時的にクリエイティブ飛行、壁貫通をすることができるようになるバフを自分自身につける。壁貫通時、地面の透視は不可能");
        addSpell(SSSpellRegistry.POLTERGEIST, "ポルターガイスト", "亡霊の力で範囲内のターゲットを上空に打ち上げた後、詠唱者が向いている方向に向けて飛ばす。その後飛ばされた相手に低速落下をつけ、地面に着地した時、落下時間に応じたダメージを与える");

        // Death Attack
        add("death.attack.spector_spellbooks.spector_arrow", "%1$sは %2$sの矢で貫かれた");
        add("death.attack.spector_spellbooks.spector_bolt", "%1$sは %2$sによって墓に導かれた");
        add("death.attack.spector_spellbooks.ray_of_spector", "%1$sは %2$sの亡霊の光線によってあの世に送られた");
        add("death.attack.spector_spellbooks.spector_slash", "%1$sの命は %2$sの亡霊によって断ち切られた");
        add("death.attack.spector_spellbooks.spector_breath", "%1$sは %2$sの背筋の凍るような息によって憑かれた");
        add("death.attack.spector_spellbooks.poltergeist", "%1$sは %2$sのポルターガイストによって引きずり回された");

        // Ui
        addUi("fall_damage_ratio", "落下時ダメージ倍率: %s%%");
    }
}
