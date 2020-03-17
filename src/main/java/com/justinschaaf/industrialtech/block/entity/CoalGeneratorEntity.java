package com.justinschaaf.industrialtech.block.entity;

import com.justinschaaf.industrialtech.util.FluxIOState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DefaultedList;

public class CoalGeneratorEntity extends AbstractMachineEntity {

    private int burnTime;
    private int maxBurnTime;

    public CoalGeneratorEntity() {
        super(ModBlockEntityRegistry.COAL_GENERATOR, DefaultedList.ofSize(2, ItemStack.EMPTY));
        this.setDefaultFluxIO(FluxIOState.OUTPUT);
    }

    @Override
    public void tick() {

        if (this.burnTime > 0) {

            if (getFlux().receiveFlux(8, true) > 0) {

                getFlux().receiveFlux((int) Math.round(8 * getUpgradeMultiplier()));
                this.burnTime--;
                markDirty();

            }

        } else {

            this.maxBurnTime = 0;

            if (AbstractFurnaceBlockEntity.canUseAsFuel(getInvStack(1))) {

                this.maxBurnTime = AbstractFurnaceBlockEntity.createFuelTimeMap().get(getInvStack(1).getItem());
                this.burnTime = this.maxBurnTime;

                takeInvStack(1, 1);

            }

        }

        this.pushFlux();
        super.tick();

    }

    @Override
    public void updateProperties() {

        if (this.getWorld() != null) if (this.getWorld().isClient()) return;

        super.updateProperties();

        this.getPropertyDelegate().set(2, this.maxBurnTime);
        this.getPropertyDelegate().set(3, this.burnTime);

    }

    @Override
    public void updateBlockstates() {
        super.updateBlockstates();

        if (this.getWorld() != null) {

            if (this.getWorld().isClient()) return;

            if (this.burnTime > 0) this.getWorld().setBlockState(this.getPos(), this.getWorld().getBlockState(this.getPos()).with(Properties.ENABLED, true));
            else this.getWorld().setBlockState(this.getPos(), this.getWorld().getBlockState(this.getPos()).with(Properties.ENABLED, false));

        }

    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {

        super.toTag(tag);

        tag.putInt("burnTime", this.burnTime);
        tag.putInt("maxBurnTime", this.maxBurnTime);

        return tag;

    }

    @Override
    public void fromTag(CompoundTag tag) {

        super.fromTag(tag);

        this.burnTime = tag.getInt("burnTime");
        this.maxBurnTime = tag.getInt("maxBurnTime");

    }

}
