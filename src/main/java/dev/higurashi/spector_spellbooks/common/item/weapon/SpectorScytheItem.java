package dev.higurashi.spector_spellbooks.common.item.weapon;

import dev.higurashi.spector_spellbooks.registry.SSSpellRegistry;
import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class SpectorScytheItem extends MagicSwordItem {
    public SpectorScytheItem(Properties properties) {
        super(SSWeaponTear.SPECTOR_SCYTHE, properties, SpellDataRegistryHolder.of(new SpellDataRegistryHolder(SSSpellRegistry.SPECTOR_SLASH, 7)));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> components, @NotNull TooltipFlag isAdvanced) {
        AbstractSpell spell = SSSpellRegistry.SPECTOR_SLASH.get();
        components.add(Component.translatable("tooltip.irons_spellbooks.enhance_spell_level", Component.translatable(spell.getComponentId()).withStyle(spell.getSchoolType().getDisplayName().getStyle()))
                .withStyle(ChatFormatting.YELLOW));
                ;

        super.appendHoverText(stack, level, components, isAdvanced);
    }
}
