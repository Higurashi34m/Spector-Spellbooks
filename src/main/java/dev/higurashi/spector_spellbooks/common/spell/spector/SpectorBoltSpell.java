package dev.higurashi.spector_spellbooks.common.spell.spector;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.SpectorBoltEntity;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpectorBoltSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = SpectorSpellbooks.id("bolt");
    private static final DefaultConfig CONFIG = new DefaultConfig()
            .setSchoolResource(SSSchoolRegistry.SPECTOR_RESOURCE)
            .setMinRarity(SpellRarity.RARE)
            .setCooldownSeconds(10)
            .setMaxLevel(6).build();

    public SpectorBoltSpell() {
        this.baseManaCost = 80;
        this.baseSpellPower = 2;
        this.manaCostPerLevel = 5;
        this.spellPowerPerLevel = 1;
    }

    @Override public ResourceLocation getSpellResource() { return SPELL_ID; }
    @Override public DefaultConfig getDefaultConfig() { return CONFIG; }

    @Override public CastType getCastType() { return CastType.INSTANT; }

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(this.getDamage(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(this.getDuration(spellLevel), 1))
        );
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData magicData) {
        float damage = this.getDamage(spellLevel, caster);
        int duration = this.getDuration(spellLevel);

        SpectorBoltEntity bolt = new SpectorBoltEntity(level, caster, caster.getEyePosition(), damage, duration);
        bolt.shoot(caster.getLookAngle());
        bolt.setXRot(caster.getXRot());
        bolt.setYRot(caster.getYRot());
        level.addFreshEntity(bolt);

        super.onCast(level, spellLevel, caster, source, magicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) { return this.getSpellPower(spellLevel, caster); }
    private int getDuration(int spellLevel) { return 160 + spellLevel * 20; }
}
