package com.justinschaaf.industrialtech.block.entity;

import com.justinschaaf.industrialtech.block.AbstractMachine;
import com.justinschaaf.industrialtech.gui.InventoryImpl;
import com.justinschaaf.industrialtech.gui.MapPropertyDelegate;
import com.justinschaaf.industrialtech.item.Upgrade;
import com.justinschaaf.industrialtech.util.FluxIOState;
import com.justinschaaf.industrialtech.util.flux.Flux;
import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import szewek.fabricflux.api.IFlux;
import szewek.fabricflux.api.IFluxContainer;

import java.util.HashMap;
import java.util.Optional;

public abstract class AbstractMachineEntity extends BlockEntity implements InventoryImpl, IFluxContainer, PropertyDelegateHolder, Tickable {

    private DefaultedList<ItemStack> items;
    private Flux flux = new Flux();
    private HashMap<Direction, FluxIOState> fluxIo = new HashMap<>();
    private MapPropertyDelegate propertyDelegate = new MapPropertyDelegate();

    public AbstractMachineEntity(BlockEntityType<?> type, DefaultedList<ItemStack> items) {

        super(type);
        this.items = items;

        // Update properties and blockstates
        updateProperties();
        updateBlockstates();

    }

    /*
     * Working
     */

    @Override
    public void tick() {

        if (this.getFlux().getFluxCapacity() != (int) (Flux.BASE_FLUX * getUpgradeMultiplier())) this.getFlux().setFluxCapacity((int) (Flux.BASE_FLUX * getUpgradeMultiplier()));

        updateProperties();
        updateBlockstates();

    }

    /*
     * Property Delegate
     */

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return this.propertyDelegate;
    }

    public void updateProperties() {

        if (this.getWorld() != null) if (this.getWorld().isClient()) return;

        this.getPropertyDelegate().set(0, this.getFlux().getFluxCapacity());
        this.getPropertyDelegate().set(1, this.getFlux().getFluxAmount());

    }

    /*
     * Blockstates
     */

    public void updateBlockstates() {

        if (this.getWorld() != null) {

            if (this.getWorld().isClient()) return;

            this.getWorld().setBlockState(this.getPos(), this.getWorld().getBlockState(this.getPos()).with(AbstractMachine.UPGRADE_LEVEL, getUpgradeLevel()));

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
        FluxIOState.toTag(tag, getFluxIo());

        return tag;

    }

    @Override
    public void fromTag(CompoundTag tag) {

        super.fromTag(tag);

        Inventories.fromTag(tag, getItems());
        this.getFlux().fromTag(tag);
        this.fluxIo = FluxIOState.fromTag(tag);

    }

    /*
     * Inventory
     */

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.items;
    }

    // Get upgrade
    public ItemStack getUpgrade() {
        if (getInvStack(0) == null) return ItemStack.EMPTY;
        else return getInvStack(0);
    }

    public int getUpgradeLevel() {
        if (getUpgrade().getItem() instanceof Upgrade) return ((Upgrade) getUpgrade().getItem()).getUpgradeLevel();
        else return 0;
    }

    public double getUpgradeMultiplier() {
        if (getUpgrade().getItem() instanceof Upgrade) return ((Upgrade) getUpgrade().getItem()).getMultiplier();
        else return 1;
    }

    /*
     * Fabric Flux Impl
     */

    public void pushFlux() {

        if (this.getWorld() == null) return;

        Direction[] directions = Direction.values();
        IFlux needyNeighbor = null;

        for (Direction d : directions) {

            if (this.getFluxIo().get(d) == FluxIOState.OUTPUT || this.getFluxIo().get(d) == FluxIOState.INPUT_OUTPUT) {

                BlockPos neighbor = this.getPos().offset(d);

                if (this.getWorld().getBlockEntity(neighbor) instanceof IFluxContainer) {

                    Optional<IFlux> neighborFlux = ((IFluxContainer) this.getWorld().getBlockEntity(neighbor)).getFluxFor(d.getOpposite());

                    if (neighborFlux.isPresent()) {

                        if (needyNeighbor == null) needyNeighbor = neighborFlux.get();
                        else if ((needyNeighbor.getFluxAmount() / needyNeighbor.getFluxCapacity()) > (neighborFlux.get().getFluxAmount() / neighborFlux.get().getFluxCapacity()))
                            needyNeighbor = neighborFlux.get();

                    }

                }

            }

        }

        if (needyNeighbor != null) getFlux().to(needyNeighbor, getFluxPushAmount());

    }

    public int getFluxPushAmount() {
        return (int) (((float) Flux.BASE_FLUX / 16) * getUpgradeMultiplier());
    }

    public Flux getFlux() {
        return this.flux;
    }

    @Override
    public Optional<IFlux> getFluxFor(Object object) {
        if (object instanceof Direction) {
            FluxIOState ioState = this.getFluxIo().get(object);
            if (ioState == FluxIOState.INPUT || ioState == FluxIOState.INPUT_OUTPUT) return Optional.of(this.getFlux());
        }
        return Optional.empty();
    }

    public HashMap<Direction, FluxIOState> getFluxIo() {
        return this.fluxIo;
    }

    public void setDefaultFluxIO(FluxIOState io) {

        if (this.getFluxIo().isEmpty()) for (Direction d : Direction.values()) this.getFluxIo().put(d, io);

    }

}
