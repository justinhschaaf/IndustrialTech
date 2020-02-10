package com.justinschaaf.industrialtech.gui;

import com.justinschaaf.industrialtech.gui.widgets.WSlot;
import com.justinschaaf.industrialtech.item.Upgrade;
import com.justinschaaf.industrialtech.main.Reference;
import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.ValidatedSlot;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;

public abstract class AbstractMachineController extends CottonCraftingController {

    private WPlainPanel machine;
    private WPlainPanel playerInv;

    public AbstractMachineController(int syncId, PlayerInventory playerInventory, BlockContext context, String identifier, boolean requiresProgress) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        // Init parent
        setRootPanel(new WPlainPanel());

        // Machine
        setMachine(new WPlainPanel());
        getMachine().setSize(176, 100); // 176, 100
        getMachine().setBackgroundPainter(BackgroundPainter.VANILLA);

        WLabel machineName = new WLabel(new TranslatableText("block.industrial_tech." + identifier).asString());
        getMachine().add(machineName, 0, 0);

        WSlot<UpgradeSlot> upgrade = new WSlot<>(UpgradeSlot.class, blockInventory, 0);
        getMachine().add(upgrade, 0, (18 * 3) + 10); // 0, 3

        // Machine Bars

        WBar energyBar = new WBar(Reference.Textures.ENERGY_BAR_BG, Reference.Textures.ENERGY_BAR_FG, 1, 0, WBar.Direction.RIGHT);
        getMachine().add(energyBar, 18, 18*4, 144, 9);
        if (propertyDelegate != null) energyBar.withTooltip(new TranslatableText("industrial_tech.tooltip_energy", getPropertyDelegate().get(1), getPropertyDelegate().get(0), new TranslatableText("industrial_tech.energy_unit").asString()));

        if (requiresProgress) {

            WBar progressBar = new WBar(Reference.Textures.PROGRESS_BAR_BG, Reference.Textures.PROGRESS_BAR_FG, 3, 2, WBar.Direction.RIGHT);
            getMachine().add(progressBar, 18, 63, 144, 9);
            if (propertyDelegate != null) progressBar.withTooltip(new TranslatableText("industrial_tech.tooltip_progress", getPropertyDelegate().get(3), getPropertyDelegate().get(2)));

        }

        // Player inventory
        setPlayerInv(new WPlainPanel());
        getPlayerInv().setSize(100, 176);
        getPlayerInv().setBackgroundPainter(BackgroundPainter.VANILLA);
        getPlayerInv().add(new WPlayerInvPanel(playerInventory), 0, 10); // 0, 104

        WLabel inventoryName = new WLabel(new TranslatableText("container.inventory").asString());
        getPlayerInv().add(inventoryName, 0, 0);

    }

    public void createPanel() {

        getRootPanel().add(machine, 0, 0, 162, 72); // 162, 80
        getRootPanel().add(playerInv, 0, 106); // 162, 80

        getRootPanel().validate(this);

    }

    @Override
    public void addPainters() {
        // We don't need this for our gui...
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        if (slot.inventory == blockInventory && slot.id == 0 && !(stack.getItem() instanceof Upgrade)) return false;
        return super.canInsertIntoSlot(stack, slot);
    }

    @Override
    protected boolean insertItem(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        System.out.println(startIndex + " " + endIndex);
        return super.insertItem(stack, startIndex, endIndex, fromLast);
    }

    @Override
    public WPlainPanel getRootPanel() {
        return (WPlainPanel) super.getRootPanel();
    }

    public WPlainPanel getMachine() {
        return machine;
    }

    public void setMachine(WPlainPanel machine) {
        this.machine = machine;
    }

    public WPlainPanel getPlayerInv() {
        return playerInv;
    }

    public void setPlayerInv(WPlainPanel playerInv) {
        this.playerInv = playerInv;
    }

    public static class UpgradeSlot extends ValidatedSlot {

        public UpgradeSlot(Inventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            if (stack.getItem() instanceof Upgrade) return super.canInsert(stack);
            else return false;
        }

    }

}
