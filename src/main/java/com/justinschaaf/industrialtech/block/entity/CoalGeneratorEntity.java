package com.justinschaaf.industrialtech.block.entity;

import com.justinschaaf.industrialtech.block.AbstractMachine;
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

        // Update properties and blockstates
        updateProperties();
        updateBlockstates();

    }

    @Override
    public void tick() {

        if (burnTime > 0) {

            if (getFlux().receiveFlux(8, true) > 0) {

                getFlux().receiveFlux((int) Math.round(8 * getUpgradeMultiplier()));
                burnTime--;
                markDirty();

            }

        } else if (burnTime <= 0) {

            maxBurnTime = 0;

            if (AbstractFurnaceBlockEntity.canUseAsFuel(getInvStack(1))) {

                maxBurnTime = AbstractFurnaceBlockEntity.createFuelTimeMap().get(getInvStack(1).getItem());
                burnTime = maxBurnTime;

                takeInvStack(1, 1);

            }

        }

        updateProperties();
        updateBlockstates();

    }

    @Override
    public void updateProperties() {

        if (world != null) if (world.isClient) return;

        super.updateProperties();

        this.getPropertyDelegate().set(2, maxBurnTime);
        this.getPropertyDelegate().set(3, burnTime);

    }

    @Override
    public void updateBlockstates() {
        super.updateBlockstates();

        if (world != null) {

            if (burnTime > 0) world.setBlockState(pos, world.getBlockState(pos).with(Properties.ENABLED, true));
            else world.setBlockState(pos, world.getBlockState(pos).with(Properties.ENABLED, false));

        }

    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {

        super.toTag(tag);

        tag.putInt("burnTime", burnTime);
        tag.putInt("maxBurnTime", maxBurnTime);

        return tag;

    }

    @Override
    public void fromTag(CompoundTag tag) {

        super.fromTag(tag);

        this.burnTime = tag.getInt("burnTime");
        this.maxBurnTime = tag.getInt("maxBurnTime");

    }

}
