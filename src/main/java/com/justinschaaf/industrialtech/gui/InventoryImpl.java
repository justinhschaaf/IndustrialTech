package com.justinschaaf.industrialtech.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

public interface InventoryImpl extends Inventory {

    abstract DefaultedList<ItemStack> getItems();

    @Override
    default int getInvSize() {
        return getItems().size();
    }

    @Override
    default boolean isInvEmpty() {
        for (ItemStack stack : getItems()) if (!stack.isEmpty()) return false;
        return true;
    }

    @Override
    default ItemStack getInvStack(int slot) {
        return getItems().get(slot);
    }

    @Override
    default ItemStack takeInvStack(int slot, int amount) {
        return Inventories.splitStack(getItems(), slot, amount);
    }

    @Override
    default ItemStack removeInvStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

    @Override
    default void setInvStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
    }

    @Override
    default boolean canPlayerUseInv(PlayerEntity player) {
        return true;
    }

    @Override
    default void clear() {
        getItems().clear();
    }

}
