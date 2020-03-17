package com.justinschaaf.industrialtech.gui.controllers;

import com.justinschaaf.industrialtech.gui.widgets.WMachineIO;
import com.justinschaaf.industrialtech.gui.widgets.WSpecialSlot;
import com.justinschaaf.industrialtech.gui.widgets.WSuppliedLabel;
import com.justinschaaf.industrialtech.util.Reference;
import io.github.cottonmc.cotton.gui.ValidatedSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import szewek.fabricflux.api.IFluxContainer;

public class CapacitorController extends AbstractMachineController {

    public CapacitorController(int syncId, PlayerInventory playerInventory, BlockContext context) {

        super(syncId, playerInventory, context, Reference.Blocks.CAPACITOR, false);

        // IO Label
        WLabel ioLabel = new WSuppliedLabel(() -> new TranslatableText(Reference.Texts.ENERGY_IO, this.getPropertyDelegate().get(2), new TranslatableText(Reference.Texts.ENERGY_UNIT).asString()), this.getIOColor(getPropertyDelegate().get(2)));
        this.getMachine().add(ioLabel, 0, 10);

        // Rechargeable Slots
        WSpecialSlot<RechargableSlot> rechargeableItemSlot = new WSpecialSlot<>(RechargableSlot.class, this.blockInventory, 1, 4, 1);
        this.getMachine().add(rechargeableItemSlot, 0, (int) (18 * 1.5) + 10);

        // IO Control
        WMachineIO ioControl = new WMachineIO(context);
        this.getMachine().add(ioControl, 18 * 6, 0);

        this.createPanel();

    }

    private int getIOColor(int io) {
        if (io > 0) return Formatting.GREEN.getColorValue();
        else if (io < 0) return Formatting.RED.getColorValue();
        else return WLabel.DEFAULT_TEXT_COLOR;
    }

    public static class RechargableSlot extends ValidatedSlot {

        public RechargableSlot(Inventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            if (stack.getItem() instanceof IFluxContainer) return super.canInsert(stack);
            else return false;
        }

    }

}
