package dev.higurashi.spector_spellbooks.common.item.weapon;

import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.ExtendedWeaponTier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class SSWeaponTear extends ExtendedWeaponTier {
    public SSWeaponTear(int uses, float damage, float speed, int enchantmentValue, Supplier<Ingredient> repairIngredient, AttributeContainer... attributes) {
        super(uses, damage, speed, enchantmentValue, repairIngredient, attributes);
    }

    public static ExtendedWeaponTier SPECTOR_SCYTHE = new SSWeaponTear(2031, 12, -2.6f, 16, () -> Ingredient.of(Items.NETHERITE_SCRAP));
}
