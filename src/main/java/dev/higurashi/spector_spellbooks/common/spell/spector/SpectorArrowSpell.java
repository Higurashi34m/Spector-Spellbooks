package dev.higurashi.spector_spellbooks.common.spell.spector;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.SpectorArrowEntity;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpectorArrowSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = SpectorSpellbooks.id("spector_arrow");
    private static final DefaultConfig CONFIG = new DefaultConfig()
            .setSchoolResource(SSSchoolRegistry.SPECTOR_RESOURCE)
            .setMinRarity(SpellRarity.COMMON)
            .setCooldownSeconds(10)
            .setMaxLevel(10).build();

    public SpectorArrowSpell() {
        this.castTime = 30;
        this.baseManaCost = 40;
        this.baseSpellPower = 6;
        this.manaCostPerLevel = 5;
        this.spellPowerPerLevel = 2;
    }

    @Override public ResourceLocation getSpellResource() { return SPELL_ID; }
    @Override public DefaultConfig getDefaultConfig() { return CONFIG; }

    @Override public CastType getCastType() { return CastType.LONG; }

    @Override public AnimationHolder getCastStartAnimation() { return SpellAnimations.BOW_CHARGE_ANIMATION; }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData magicData) {
        float damage = this.getDamage(spellLevel, caster);

        Vec3 spawnPos = caster.getEyePosition().add(caster.getForward().scale(0.75));

        SpectorArrowEntity arrow = new SpectorArrowEntity(level, caster, spawnPos, damage);
        arrow.shoot(caster.getLookAngle());
        level.addFreshEntity(arrow);

        super.onCast(level, spellLevel, caster, source, magicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) { return this.getSpellPower(spellLevel, caster); }
}
