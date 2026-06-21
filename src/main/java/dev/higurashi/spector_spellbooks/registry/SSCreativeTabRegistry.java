package dev.higurashi.spector_spellbooks.registry;

import dev.higurashi.spector_spellbooks.SpectorSpellbooks;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SSCreativeTabRegistry {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpectorSpellbooks.MOD_ID);
    public static void register(IEventBus eventBus) { CREATIVE_TAB.register(eventBus); }

    public static final RegistryObject<CreativeModeTab> SPECTOR_SPELLBOOKS = CREATIVE_TAB.register("spector_spellbooks", () -> CreativeModeTab.builder()
            .title(Component.translatable("creative_tab." + SpectorSpellbooks.MOD_ID))
            .icon(() -> new ItemStack(SSItemRegistry.SPECTOR_SPELLBOOK.get()))
            .displayItems((context, output) -> {
                output.accept(SSItemRegistry.SPECTOR_ESSENCE.get());
                output.accept(SSItemRegistry.SPECTOR_LUMP_INGOT.get());
                output.accept(SSItemRegistry.SPECTOR_RUNE.get());
                output.accept(SSItemRegistry.SPECTOR_UPGRADE_ORB.get());
                output.accept(SSItemRegistry.SPECTOR_SPELLBOOK.get());
                output.accept(SSItemRegistry.SPECTOR_SCYTHE.get());
                output.accept(SSItemRegistry.SPECTOR_HELMET.get());
                output.accept(SSItemRegistry.SPECTOR_CHESTPLATE.get());
                output.accept(SSItemRegistry.SPECTOR_LEGGINGS.get());
                output.accept(SSItemRegistry.SPECTOR_BOOTS.get());
                SSSpellRegistry.SPELLS.getEntries().forEach(registry -> {
                    AbstractSpell spell = registry.get();
                    for (int i = spell.getMinLevel(); i <= spell.getMaxLevel(); i++) {
                        ItemStack stack = new ItemStack(ItemRegistry.SCROLL.get());
                        ISpellContainer.createScrollContainer(spell, i, stack);
                        output.accept(stack);
                    }
                });
            })
            .build());
}
