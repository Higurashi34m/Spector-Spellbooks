package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SSTagRegistry {
    public static final TagKey<Item> SPECTOR_FOCUS = ItemTags.create(SpectorSpellbooks.id("spector_focus"));
}
