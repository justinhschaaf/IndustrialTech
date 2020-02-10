package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.main.Reference;
import com.justinschaaf.industrialtech.util.Flux;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import szewek.fabricflux.api.IFlux;
import szewek.fabricflux.api.IFluxContainer;

import java.util.List;
import java.util.Optional;

public class EnergyConsumer extends BasicItem implements IFluxContainer {

    private Flux flux;

    public EnergyConsumer(Settings settings) {
        this(Flux.BASE_FLUX, settings);
    }

    public EnergyConsumer(int fluxCapacity, Settings settings) {
        super(settings.maxDamage(16));

        this.flux = new Flux(fluxCapacity);

    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        // Init NBT tags
        if (stack.getTag() == null) stack.getOrCreateTag();
        if (!stack.getTag().contains("capacity")) flux.toTag(stack.getOrCreateTag());

        if (stack.getItem() != null && stack.getItem() instanceof EnergyConsumer) {

            EnergyConsumer ec = (EnergyConsumer) stack.getItem();

            ec.getFlux().fromTag(stack.getTag());

            // Update damage bar
            stack.setDamage((int) ((ec.getFlux().getFluxAmount() / (float) ec.getFlux().getFluxCapacity()) * 16));

        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    /*
     * Aesthetics
     */

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {

        // Energy amount
        tooltip.add(new TranslatableText(Reference.Texts.TOOLTIP_ENERGY, getFlux().getFluxAmount(), getFlux().getFluxCapacity(), new TranslatableText(Reference.Texts.ENERGY_UNIT)).formatted(Formatting.DARK_AQUA));

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

    public Flux getFlux() {
        return flux;
    }

    @Override
    public Optional<IFlux> getFluxFor(Object object) {
        return Optional.of(getFlux());
    }

}
