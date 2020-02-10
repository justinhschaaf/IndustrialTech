package com.justinschaaf.industrialtech.block;

import com.justinschaaf.industrialtech.block.entity.CoalGeneratorEntity;
import com.justinschaaf.industrialtech.main.Reference;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
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

        if (world.isClient) return ActionResult.PASS;

        CoalGeneratorEntity be = (CoalGeneratorEntity) world.getBlockEntity(pos);

        /*if (player.isSneaking()) {
            // Test code
            EnergyStorage es = be.getEnergyStorage();
            player.addChatMessage(new TranslatableText("block.industrial_engineering.coal_generator.storage", es.getEnergyStored(), es.getEnergyCapacity(), new TranslatableText("industrial_engineering.energy_unit").asString()), true);
        } else {
            // Non-test code
            if (be != null) ContainerProviderRegistry.INSTANCE.openContainer(new Identifier(Reference.MODID, "coal_generator"), player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
        }*/

        if (be != null) ContainerProviderRegistry.INSTANCE.openContainer(Reference.Blocks.COAL_GENERATOR, player, (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));

        return ActionResult.SUCCESS;
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
