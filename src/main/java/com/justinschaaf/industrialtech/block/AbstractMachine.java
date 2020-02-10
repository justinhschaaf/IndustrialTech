package com.justinschaaf.industrialtech.block;

import com.justinschaaf.industrialtech.block.entity.AbstractMachineEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public abstract class AbstractMachine extends HorizontalFacingBlock implements BlockEntityProvider {

    public static IntProperty UPGRADE_LEVEL = IntProperty.of("upgrade_level", 0, 2);

    public AbstractMachine(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(Properties.ENABLED, false).with(UPGRADE_LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(Properties.ENABLED);
        builder.add(UPGRADE_LEVEL);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // TODO Fix bug where upgrade blockstates don't show after world reload -- probably possible by getting blockstate stored in item
        return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(AbstractMachine.UPGRADE_LEVEL, ((AbstractMachineEntity) ctx.getWorld().getBlockEntity(ctx.getBlockPos())).getUpgradeLevel());
    }

}
