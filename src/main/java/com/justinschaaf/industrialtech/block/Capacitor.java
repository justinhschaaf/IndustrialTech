package com.justinschaaf.industrialtech.block;

import com.justinschaaf.industrialtech.block.entity.CapacitorEntity;
import com.justinschaaf.industrialtech.util.Reference;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Capacitor extends AbstractMachine {

    public Capacitor(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return openContainer(Reference.Blocks.CAPACITOR, world, pos, player, hand);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new CapacitorEntity();
    }

}
