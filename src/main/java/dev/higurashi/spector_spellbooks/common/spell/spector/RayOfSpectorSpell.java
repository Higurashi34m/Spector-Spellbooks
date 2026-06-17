package dev.higurashi.spector_spellbooks.common.spell.spector;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.entity.projectile.RayOfSpectorEntity;
import dev.higurashi.spector_spellbooks.registry.SSSchoolRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.RaycastBuilder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class RayOfSpectorSpell extends AbstractSpell {
    private static final ResourceLocation SPELL_ID = SpectorSpellbooks.id("ray_of_spector");
    private static final DefaultConfig CONFIG = new DefaultConfig()
            .setSchoolResource(SSSchoolRegistry.SPECTOR_RESOURCE)
            .setMinRarity(SpellRarity.RARE)
            .setCooldownSeconds(10)
            .setMaxLevel(7).build();

    public RayOfSpectorSpell() {
        this.baseManaCost = 40;
        this.baseSpellPower = 4;
        this.manaCostPerLevel = 5;
        this.spellPowerPerLevel = 2;
    }

    @Override public ResourceLocation getSpellResource() { return SPELL_ID; }
    @Override public DefaultConfig getDefaultConfig() { return CONFIG; }

    @Override public CastType getCastType() { return CastType.INSTANT; }
    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(this.getDamage(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.distance", this.getRange(spellLevel))
        );
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity caster, CastSource source, MagicData magicData) {
        float damage = this.getDamage(spellLevel, caster);
        float range = this.getRange(spellLevel);

        HitResult result = RaycastBuilder.begin(level, caster)
                .range(range)
                .checkForBlocks(false)
                .bbInflation(0.15f)
                .build();

        level.addFreshEntity(new RayOfSpectorEntity(level, caster, caster.getEyePosition(), result.getLocation()));

        if (result.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult) result).getEntity();
            DamageSources.applyDamage(target, damage, getDamageSource(caster).indirect());
        }

        super.onCast(level, spellLevel, caster, source, magicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) { return this.getSpellPower(spellLevel, caster); }
    private float getRange(int spellLevel) { return 15.0f + spellLevel * 2.5f ; }
}
