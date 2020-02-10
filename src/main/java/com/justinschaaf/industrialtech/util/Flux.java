package com.justinschaaf.industrialtech.util;

import net.minecraft.nbt.CompoundTag;
import szewek.fabricflux.api.IFlux;

public class Flux implements IFlux {

    public static final int BASE_FLUX = 65536;

    private int capacity;
    private int storage;

    public Flux() {
        this(BASE_FLUX, 0);
    }

    public Flux(int capacity) {
        this(capacity, 0);
    }

    public Flux(int capacity, int amount) {
        this.capacity = capacity;
        this.storage = amount;
    }

    @Override
    public int getFluxCapacity() {
        return this.capacity;
    }

    public void setFluxCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getFluxAmount() {
        return this.storage;
    }

    public void setFluxAmount(int amount) {
        this.storage = amount;
    }

    public int extractFlux(int n) {
        return this.extractFlux(n, false);
    }

    @Override
    public int extractFlux(int n, boolean sim) {
        int amt = (getFluxAmount() - n >= 0) ? n : getFluxAmount();
        if (!sim) this.storage -= amt;
        return amt;
    }

    public int receiveFlux(int n) {
        return this.receiveFlux(n, false);
    }

    @Override
    public int receiveFlux(int n, boolean sim) {
        int amt = Math.min(getFluxCapacity() - getFluxAmount(), n);
        if (!sim) this.storage += amt;
        return amt;
    }

    public CompoundTag toTag(CompoundTag tag) {

        tag.putInt("capacity", getFluxCapacity());
        tag.putInt("storage", getFluxAmount());

        return tag;

    }

    public void fromTag(CompoundTag tag) {

        this.capacity = tag.getInt("capacity");
        this.storage = tag.getInt("storage");

    }

}
