package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.util.Reference;
import com.justinschaaf.industrialtech.util.flux.Flux;
import com.justinschaaf.industrialtech.util.flux.StackFlux;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import szewek.fabricflux.api.IFlux;
import szewek.fabricflux.api.IFluxContainer;

import java.util.List;
import java.util.Optional;

public class EnergyConsumer extends ITItem implements IFluxContainer {

    public EnergyConsumer(Settings settings) {
        this(Flux.BASE_FLUX, settings);
    }

    public EnergyConsumer(int fluxCapacity, Settings settings) {
        super(settings.maxDamage(fluxCapacity).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        stack.setDamage(stack.getMaxDamage());
        super.onCraft(stack, world, player);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {

        if (this.isIn(group)) {

            // Empty Stack
            ItemStack damagedStack = new ItemStack(this);
            damagedStack.setDamage(getMaxDamage());
            stacks.add(damagedStack);

            // Full stack
            stacks.add(new ItemStack(this));

        }

    }

    /*
     * Aesthetics
     */

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

        // Energy amount
        getFluxFor(stack).ifPresent((flux) -> tooltip.add(new TranslatableText(Reference.Texts.TOOLTIP_ENERGY, flux.getFluxAmount(), flux.getFluxCapacity(), new TranslatableText(Reference.Texts.ENERGY_UNIT)).formatted(Formatting.GOLD)));

        super.appendTooltip(stack, world, tooltip, context);

    }

    @Override
    public boolean hasEnchantmentGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    /*
     * Fabric Flux
     */

    public Flux getFlux(ItemStack stack) {
        return new StackFlux(stack);
    }

    @Override
    public Optional<IFlux> getFluxFor(Object object) {
        if (object instanceof ItemStack && ((ItemStack) object).getItem() instanceof EnergyConsumer) return Optional.of(getFlux((ItemStack) object));
        else return Optional.empty();
    }

}
