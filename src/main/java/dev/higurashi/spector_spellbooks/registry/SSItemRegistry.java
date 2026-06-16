package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.item.SpectorArmor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SSItemRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }

    public static final RegistryObject<Item> SPECTOR_ESSENCE = ITEMS.register("spector_essence", () -> new Item(new Item.Properties()));

    // Armor
    public static final RegistryObject<Item> SPECTOR_HELMET = ITEMS.register("spector_helmet", () -> new SpectorArmor(ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPECTOR_CHESTPLATE = ITEMS.register("spector_chestplate", () -> new SpectorArmor(ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPECTOR_LEGGINGS = ITEMS.register("spector_leggings", () -> new SpectorArmor(ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPECTOR_BOOTS = ITEMS.register("spector_boots", () -> new SpectorArmor(ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));
}
