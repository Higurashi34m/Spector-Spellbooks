package dev.higurashi.spector_spellbooks.common.spell.spector;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.registry.SSEffectRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PoltergeistSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = SpectorSpellbooks.id("poltergeist");
    private static final DefaultConfig CONFIG = new DefaultConfig()
            .setSchoolResource(SSSchoolRegistry.SPECTOR_RESOURCE)
            .setMinRarity(SpellRarity.EPIC)
            .setCooldownSeconds(25)
            .setMaxLevel(4).build();

    public PoltergeistSpell() {
        this.castTime = 40;
        this.baseManaCost = 100;
        this.baseSpellPower = 2;
        this.manaCostPerLevel = 25;
        this.spellPowerPerLevel = 2;
    }

    @Override public ResourceLocation getSpellResource() { return SPELL_ID; }
    @Override public DefaultConfig getDefaultConfig() { return CONFIG; }

    @Override public CastType getCastType() { return CastType.LONG; }

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", getRadius(spellLevel)),
                Component.translatable("ui.spector_spellbooks.fall_damage_ratio", Utils.stringTruncation(getDamageRatio(spellLevel, caster) * 100, 0))
        );
    }

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity caster, MagicData magicData) {
        return Utils.preCastTargetHelper(level, caster, magicData, this, 20, 0.1f);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData magicData) {
        if (magicData.getAdditionalCastData() instanceof TargetEntityCastData castData) {
            int radius = this.getRadius(spellLevel);

            LivingEntity target = castData.getTarget((ServerLevel) level);
            if (target != null) {
                List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(radius), entity -> !entity.isAlliedTo(caster) && entity != caster);
                targets.forEach(entity -> {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.5, 0.0));
                    SSEffectRegistry.POLTERGEIST.addAndTrackEffect(caster, entity, 40, spellLevel - 1);
                });
            }
        }

        super.onCast(level, spellLevel, caster, source, magicData);
    }

    private int getRadius(int spellLevel) {
        return spellLevel * 2;
    }

    public static float getDamage(int spellLevel, @Nullable LivingEntity caster) {
        return SSSpellRegistry.POLTERGEIST.get().getSpellPower(spellLevel, caster);
    }

    public static float getDamageRatio(int spellLevel, @Nullable LivingEntity caster) {
        return SSSpellRegistry.POLTERGEIST.get().getSpellPower(spellLevel, caster) / 4.0f;
    }
}
