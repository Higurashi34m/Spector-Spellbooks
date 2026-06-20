package dev.higurashi.spector_spellbooks.common.spell.spector;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.SpectorSlashEntity;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SpectorSlashSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = SpectorSpellbooks.id("spector_slash");
    private static final DefaultConfig CONFIG = new DefaultConfig()
            .setSchoolResource(SSSchoolRegistry.SPECTOR_RESOURCE)
            .setMinRarity(SpellRarity.RARE)
            .setCooldownSeconds(6)
            .setMaxLevel(7).build();

    public SpectorSlashSpell() {
        this.baseManaCost = 60;
        this.baseSpellPower = 4;
        this.manaCostPerLevel = 10;
        this.spellPowerPerLevel = 2;
    }

    @Override public ResourceLocation getSpellResource() { return SPELL_ID; }
    @Override public DefaultConfig getDefaultConfig() { return CONFIG; }

    @Override public CastType getCastType() { return CastType.INSTANT; }
    @Override public AnimationHolder getCastFinishAnimation() { return SpellAnimations.SLASH_ANIMATION; }

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(this.getDamage(spellLevel, caster), 2))
        );
    }

    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData magicData) {
        float damage = this.getDamage(spellLevel, caster);

        Vec3 spawnPos = caster.getEyePosition().add(caster.getForward().scale(0.75));

        SpectorSlashEntity arrow = new SpectorSlashEntity(level, caster, spawnPos, damage);
        arrow.shoot(caster.getLookAngle());
        level.addFreshEntity(arrow);

        super.onCast(level, spellLevel, caster, source, magicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) { return this.getSpellPower(spellLevel, caster); }
}
