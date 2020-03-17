package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.block.entity.AbstractMachineEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class Upgrade extends ITItem {

    int upgradeLevel;
    double multiplier;

    public Upgrade(Settings settings, int upgradeLevel, double multiplier) {
        super(settings);
        this.upgradeLevel = upgradeLevel;
        this.multiplier = multiplier;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public double getMultiplier() {
        return multiplier;
    }

    // Functionality

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {

        if (ctx.getWorld().getBlockEntity(ctx.getBlockPos()) == null || !(ctx.getWorld().getBlockEntity(ctx.getBlockPos()) instanceof AbstractMachineEntity)) return ActionResult.PASS;
        else {

            AbstractMachineEntity be = (AbstractMachineEntity) ctx.getWorld().getBlockEntity(ctx.getBlockPos());
            ItemStack machineUpgrade = be.getInvStack(0);

            if (!machineUpgrade.isEmpty()) { // If there is an upgrade in there, take it out

                // If the upgrade in there is better, don't bother
                if (((Upgrade) machineUpgrade.getItem()).upgradeLevel >= getUpgradeLevel()) return ActionResult.PASS;

                machineUpgrade = be.removeInvStack(0);

            }

            be.setInvStack(0, ctx.getStack());
            ctx.getPlayer().setStackInHand(ctx.getHand(), new ItemStack(Blocks.AIR));

            if (!machineUpgrade.isEmpty()) ctx.getPlayer().setStackInHand(ctx.getHand(), machineUpgrade);

            return ActionResult.SUCCESS;

        }

    }

}
