package dev.higurashi.spector_spellbooks.client.model.item;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.item.SpectorArmor;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class SpectorArmorModel extends DefaultedItemGeoModel<SpectorArmor> {
    public SpectorArmorModel() {
        super(SpectorSpellbooks.id(""));
    }

    @Override
    public ResourceLocation getModelResource(SpectorArmor object) {
        return SpectorSpellbooks.id("geo/spector_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpectorArmor object) {
        return SpectorSpellbooks.id("textures/models/armor/spector_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpectorArmor animatable) {
        return IronsSpellbooks.id("animations/wizard_armor_animation.json");
    }
}
