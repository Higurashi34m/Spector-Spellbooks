package dev.higurashi.spector_spellbooks.common.item;

import dev.higurashi.spector_spellbooks.client.model.item.SpectorArmorModel;
import dev.higurashi.spector_spellbooks.common.item.armor.SSArmorMaterials;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class SpectorArmor extends ImbuableChestplateArmorItem {
    public SpectorArmor(Type type, Properties properties) {
        super(SSArmorMaterials.SPECTOR, type, properties);
    }

    @Override
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new SpectorArmorModel());
    }
}
