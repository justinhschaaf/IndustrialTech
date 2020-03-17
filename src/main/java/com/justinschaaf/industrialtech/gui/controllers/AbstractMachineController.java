package com.justinschaaf.industrialtech.gui.controllers;

import com.justinschaaf.industrialtech.gui.widgets.WITBar;
import com.justinschaaf.industrialtech.gui.widgets.WSpecialSlot;
import com.justinschaaf.industrialtech.item.Upgrade;
import com.justinschaaf.industrialtech.util.Reference;
import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.ValidatedSlot;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class AbstractMachineController extends CottonCraftingController {

    private WPlainPanel machine;
    private WPlainPanel playerInv;

    public AbstractMachineController(int syncId, PlayerInventory playerInventory, BlockContext context, Identifier identifier, boolean requiresProgress) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        // Init parent
        this.setRootPanel(new WPlainPanel());

        // Machine
        this.setMachine(new WPlainPanel());
        this.getMachine().setSize(176, 100); // 176, 100
        this.getMachine().setBackgroundPainter(BackgroundPainter.VANILLA);

        WLabel machineName = new WLabel(new TranslatableText(Reference.Texts.getLangKeyFromIdentifier(identifier, "block")).asString());
        this.getMachine().add(machineName, 0, 0);

        WSpecialSlot<UpgradeSlot> upgrade = new WSpecialSlot<>(UpgradeSlot.class, blockInventory, 0);
        this.getMachine().add(upgrade, 0, (18 * 3) + 10); // 0, 3

        // Machine Bars

        WITBar energyBar;

        if (requiresProgress) {

            energyBar = new WITBar(Reference.Textures.ENERGY_BAR_9_BG, Reference.Textures.ENERGY_BAR_9_FG, 1, 0, WBar.Direction.RIGHT);
            this.getMachine().add(energyBar, 17, 18*4, 18*8, 9);

            WITBar progressBar = new WITBar(Reference.Textures.PROGRESS_BAR_BG, Reference.Textures.PROGRESS_BAR_FG, 3, 2, WBar.Direction.RIGHT);
            this.getMachine().add(progressBar, 17, 63, 144, 9);
            if (this.propertyDelegate != null) progressBar.withTooltip(() -> new TranslatableText(Reference.Texts.TOOLTIP_PROGRESS, this.getPropertyDelegate().get(3), this.getPropertyDelegate().get(2)));

        } else {

            energyBar = new WITBar(Reference.Textures.ENERGY_BAR_BG, Reference.Textures.ENERGY_BAR_FG, 1, 0, WBar.Direction.RIGHT);
            this.getMachine().add(energyBar, 17, (18 * 3) + 9, 18*8, 18);

        }

        if (this.propertyDelegate != null) energyBar.withTooltip(() -> new TranslatableText(Reference.Texts.TOOLTIP_ENERGY, this.getPropertyDelegate().get(1), this.getPropertyDelegate().get(0), new TranslatableText(Reference.Texts.ENERGY_UNIT).asString()));

        // Player inventory
        this.setPlayerInv(new WPlainPanel());
        this.getPlayerInv().setSize(100, 176);
        this.getPlayerInv().setBackgroundPainter(BackgroundPainter.VANILLA);
        this.getPlayerInv().add(new WPlayerInvPanel(playerInventory), 0, 10); // 0, 104

        WLabel inventoryName = new WLabel(new TranslatableText("container.inventory").asString());
        this.getPlayerInv().add(inventoryName, 0, 0);

    }

    public void createPanel() {

        this.getRootPanel().add(machine, 0, 0, 162, 72); // 162, 80
        this.getRootPanel().add(playerInv, 0, 106); // 162, 80

        this.getRootPanel().validate(this);

    }

    @Override
    public void addPainters() {
        // We don't need this for our gui...
    }

    public static BlockEntity getBlockEntity(BlockContext ctx) {
        return ctx.run(World::getBlockEntity).get();
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
