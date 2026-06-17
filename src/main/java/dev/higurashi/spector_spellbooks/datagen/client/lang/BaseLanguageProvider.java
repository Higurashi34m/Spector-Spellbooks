package dev.higurashi.spector_spellbooks.datagen.client.lang;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public abstract class BaseLanguageProvider extends LanguageProvider {
    public BaseLanguageProvider(PackOutput output, String locale) {
        super(output, SpectorSpellbooks.MOD_ID, locale);
    }

    protected void addAttribute(Supplier<Attribute> attribute, String name) {
        this.add(attribute.get().getDescriptionId(), name);
    }

    protected void addUi(String key, String name) {
        this.add("ui.spector_spellbooks." + key, name);
    }

    protected void addSpell(Supplier<AbstractSpell> spell, String name, String description) {
        this.add(spell.get().getComponentId(), name);
        this.add(spell.get().getComponentId() + "guide", description);
    }

    protected void addSchool(Supplier<SchoolType> schoolType, String name) {
        this.add("school.spector_spellbooks." + schoolType.get().getId().getPath(), name);
    }
}
