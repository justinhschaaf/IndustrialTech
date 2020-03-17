package com.justinschaaf.industrialtech.block;

import com.justinschaaf.industrialtech.block.entity.AbstractMachineEntity;
import com.justinschaaf.industrialtech.item.Upgrade;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class AbstractMachine extends HorizontalFacingBlock implements BlockEntityProvider {

    public static IntProperty UPGRADE_LEVEL = IntProperty.of("upgrade_level", 0, 2);

    public AbstractMachine(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(UPGRADE_LEVEL, 0).with(Properties.ENABLED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(UPGRADE_LEVEL);
        builder.add(Properties.ENABLED);

    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {

        BlockState state = this.getDefaultState();
        if (ctx.getStack().getTag() != null) state.with(UPGRADE_LEVEL, ctx.getStack().getTag().getCompound("BlockStateTag").getInt(UPGRADE_LEVEL.getName())); // Apply upgrade level
        return state.with(FACING, ctx.getPlayerFacing().getOpposite());

    }

    public ActionResult openContainer(Identifier identifier, World world, BlockPos pos, PlayerEntity player, Hand hand) {

        if (world.isClient || player.getStackInHand(hand).getItem() instanceof Upgrade) return ActionResult.PASS;

        AbstractMachineEntity be = (AbstractMachineEntity) world.getBlockEntity(pos);

        if (be != null) ContainerProviderRegistry.INSTANCE.openContainer(identifier, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));

        return ActionResult.SUCCESS;

    }

}
