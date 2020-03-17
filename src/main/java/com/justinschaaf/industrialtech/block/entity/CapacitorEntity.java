package com.justinschaaf.industrialtech.block.entity;

import com.justinschaaf.industrialtech.util.FluxIOState;
import com.justinschaaf.industrialtech.util.flux.Flux;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import szewek.fabricflux.api.IFlux;
import szewek.fabricflux.api.IFluxContainer;

import java.util.Optional;

public class CapacitorEntity extends AbstractMachineEntity {

    private int lastFluxAmt = 0;
    private int IO = 0;

    public CapacitorEntity() {
        super(ModBlockEntityRegistry.CAPACITOR, DefaultedList.ofSize(5, ItemStack.EMPTY));
        this.setDefaultFluxIO(FluxIOState.INPUT);
    }

    @Override
    public void tick() {

        for (int i = 1; i < getInvSize(); i++) {

            ItemStack rechargeable = getInvStack(i);

            if (rechargeable != null && !rechargeable.isEmpty()) {

                if (rechargeable.getItem() instanceof IFluxContainer) {

                    Optional<IFlux> itemFlux = ((IFluxContainer) rechargeable.getItem()).getFluxFor(rechargeable);

                    itemFlux.ifPresent(iFlux -> getFlux().to(iFlux, getFluxPushAmount()));

                }

            }

        }

        // Instead of super.tick(), calc capacity here because it needs to be higher
        if (this.getFlux().getFluxCapacity() != (int) (Flux.BASE_FLUX * Math.pow(getUpgradeMultiplier(), 2))) this.getFlux().setFluxCapacity((int) (Flux.BASE_FLUX * Math.pow(getUpgradeMultiplier(), 2)));

        this.IO = this.getFlux().getFluxAmount() - this.lastFluxAmt;
        this.lastFluxAmt = this.getFlux().getFluxAmount();

        this.pushFlux();
        updateProperties();
        updateBlockstates();

        //System.out.println(getPos().toString() + " has IO state " + this.getFluxIo().toString());

    }

    @Override
    public void updateProperties() {

        if (this.getWorld() != null) if (this.getWorld().isClient()) return;

        super.updateProperties();

        this.getPropertyDelegate().set(2, IO);

    }

    @Override
    public double getUpgradeMultiplier() {
        return super.getUpgradeMultiplier() * 8;
    }
    
}
