package com.justinschaaf.industrialtech.block;

import com.justinschaaf.industrialtech.block.entity.CoalGeneratorEntity;
import com.justinschaaf.industrialtech.util.Reference;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CoalGenerator extends AbstractMachine {

    public CoalGenerator(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return openContainer(Reference.Blocks.COAL_GENERATOR, world, pos, player, hand);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new CoalGeneratorEntity();
    }

    @Override
    public int getLuminance(BlockState state) {

        if (state.get(Properties.ENABLED)) return 8;
        else return 0;

    }

}
