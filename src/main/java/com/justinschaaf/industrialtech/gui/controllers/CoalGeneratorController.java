package com.justinschaaf.industrialtech.gui.controllers;

import com.justinschaaf.industrialtech.util.Reference;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;

public class CoalGeneratorController extends AbstractMachineController {

    public CoalGeneratorController(int syncId, PlayerInventory playerInventory, BlockContext context) {

        super(syncId, playerInventory, context, Reference.Blocks.COAL_GENERATOR, true);

        // Machine

        WItemSlot input = WItemSlot.of(blockInventory, 1);
        getMachine().add(input, 18 * 4, 18 + 10); // 4, 1

        createPanel();

    }

}
