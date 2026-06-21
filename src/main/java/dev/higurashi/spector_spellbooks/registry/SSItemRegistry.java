package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import dev.higurashi.spector_spellbooks.common.item.SSItemRarity;
import dev.higurashi.spector_spellbooks.common.item.SpectorArmor;
import dev.higurashi.spector_spellbooks.common.item.weapon.SpectorScytheItem;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.UpgradeOrbItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SSItemRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }

    public static final RegistryObject<Item> SPECTOR_ESSENCE = ITEMS.register("spector_essence", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPECTOR_LUMP_INGOT = ITEMS.register("spector_lump_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPECTOR_RUNE = ITEMS.register("spector_rune", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPECTOR_UPGRADE_ORB = ITEMS.register("spector_upgrade_orb", () -> new UpgradeOrbItem(new Item.Properties(), SSUpgradeOrbRegistry.SPECTOR_SPELL_POWER));

    // Armor
    public static final RegistryObject<Item> SPECTOR_HELMET = ITEMS.register("spector_helmet", () -> new SpectorArmor(ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPECTOR_CHESTPLATE = ITEMS.register("spector_chestplate", () -> new SpectorArmor(ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPECTOR_LEGGINGS = ITEMS.register("spector_leggings", () -> new SpectorArmor(ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SPECTOR_BOOTS = ITEMS.register("spector_boots", () -> new SpectorArmor(ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    // Weapon
    public static final RegistryObject<Item> SPECTOR_SCYTHE = ITEMS.register("spector_scythe", () -> new SpectorScytheItem(new Item.Properties().rarity(SSItemRarity.SPECTOR).fireResistant()));

    // Spellbook
    public static final RegistryObject<Item> SPECTOR_SPELLBOOK = ITEMS.register("spector_spell_book", () -> new SpellBook(10, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant())
            .withSpellbookAttributes(new AttributeContainer(SSAttributeRegistry.SPECTOR_SPELL_POWER, 0.1, AttributeModifier.Operation.MULTIPLY_BASE), new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADDITION)));
}
