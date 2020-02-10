package com.justinschaaf.industrialtech.block.entity;

import com.justinschaaf.industrialtech.block.AbstractMachine;
import com.justinschaaf.industrialtech.gui.InventoryImpl;
import com.justinschaaf.industrialtech.gui.PropertyDelegateImpl;
import com.justinschaaf.industrialtech.item.Upgrade;
import com.justinschaaf.industrialtech.util.Flux;
import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import szewek.fabricflux.api.IFlux;
import szewek.fabricflux.api.IFluxContainer;

import java.util.Optional;

public abstract class AbstractMachineEntity extends BlockEntity implements InventoryImpl, IFluxContainer, PropertyDelegateHolder, Tickable {

    private DefaultedList<ItemStack> items;
    private Flux flux = new Flux(Flux.BASE_FLUX);
    private PropertyDelegateImpl propertyDelegate = new PropertyDelegateImpl();

    public AbstractMachineEntity(BlockEntityType<?> type, DefaultedList<ItemStack> items) {
        super(type);
        this.items = items;
    }

    /*
     * Working
     */

    @Override
    public abstract void tick();

    /*
     * Property Delegate
     */

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    public void updateProperties() {

        this.getPropertyDelegate().set(0, this.getFlux().getFluxCapacity());
        this.getPropertyDelegate().set(1, this.getFlux().getFluxAmount());

    }

    /*
     * Blockstates
     */

    public void updateBlockstates() {

        if (world != null) {

            world.setBlockState(pos, world.getBlockState(pos).with(AbstractMachine.UPGRADE_LEVEL, getUpgradeLevel()));

        }

    }

    /*
     * Manipulating NBT Data
     */

    @Override
    public CompoundTag toTag(CompoundTag tag) {

        super.toTag(tag);

        Inventories.toTag(tag, getItems());
        this.getFlux().toTag(tag);

        return tag;

    }

    @Override
    public void fromTag(CompoundTag tag) {

        super.fromTag(tag);

        Inventories.fromTag(tag, getItems());
        this.getFlux().fromTag(tag);

    }

    /*
     * Inventory
     */

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    // Get upgrade
    public Upgrade getUpgrade() {
        ItemStack upgradeStack = getInvStack(0);
        if (!upgradeStack.isEmpty() && upgradeStack.getItem() instanceof Upgrade) return (Upgrade) upgradeStack.getItem();
        else return null;
    }

    public int getUpgradeLevel() {
        if (getUpgrade() != null) return getUpgrade().getUpgradeLevel();
        else return 0;
    }

    public double getUpgradeMultiplier() {
        if (getUpgrade() != null) return getUpgrade().getMultiplier();
        else return 1;
    }

    /*
     * Fabric Flux Impl
     */

    public Flux getFlux() {
        return this.flux;
    }

    @Override
    public Optional<IFlux> getFluxFor(Object object) {
        return Optional.of(this.getFlux());
    }

}
