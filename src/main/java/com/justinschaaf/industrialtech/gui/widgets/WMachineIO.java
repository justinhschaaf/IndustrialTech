package com.justinschaaf.industrialtech.gui.widgets;

import com.justinschaaf.industrialtech.util.FluxIOState;
import com.justinschaaf.industrialtech.util.PacketUtil;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.BlockContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiConsumer;

public class WMachineIO extends WPlainPanel {

    private BlockContext ctx;

    public WMachineIO(BlockContext ctx) {

        this.ctx = ctx;

        ctx.run((world, pos) -> {

            System.out.println(pos.toString());

            // Get current state
            BlockEntity be = world.getBlockEntity(pos);

            if (be != null) {

                CompoundTag beTag = be.toTag(new CompoundTag());
                HashMap<Direction, FluxIOState> io = FluxIOState.fromTag(beTag);

                // Create Buttons for Directions
                createDirectionButton(be.getCachedState().get(HorizontalFacingBlock.FACING), io, 18, 18);                                 // Front
                createDirectionButton(be.getCachedState().get(HorizontalFacingBlock.FACING).rotateYClockwise(), io, 0, 18);               // (Machine's) Right
                createDirectionButton(be.getCachedState().get(HorizontalFacingBlock.FACING).rotateYCounterclockwise(), io, 18 * 2, 18);   // (Machine's) Left
                createDirectionButton(be.getCachedState().get(HorizontalFacingBlock.FACING).getOpposite(), io, 0, 18 * 2);                // Back
                createDirectionButton(Direction.UP, io, 18, 0);                                                                                         // Up
                createDirectionButton(Direction.DOWN, io, 18, 18 * 2);                                                                                  // Down

            }

        });

    }

    private void createDirectionButton(Direction d, HashMap<Direction, FluxIOState> io, int x, int y) {

        WColoredButton b = new WColoredButton();            // Create Button
        b.setOnClick(() -> this.updateIOBlockstate(b, d));  // Set Click Action
        this.updateButtonCosmetics(b, io.get(d));           // Update Cosmetics
        this.add(b, x, y, 18, 18);            // Add to panel

    }

    private void updateIOBlockstate(WColoredButton b, Direction d) {

        this.ctx.run((world, pos) -> {

            BlockEntity be = world.getBlockEntity(pos);

            if (be != null) {

                // Get Tag
                CompoundTag beTag = be.toTag(new CompoundTag());

                System.out.println("Start: " + beTag.toString());

                // Update Data
                HashMap<Direction, FluxIOState> io = FluxIOState.fromTag(beTag);
                FluxIOState state = FluxIOState.values()[(io.get(d).id + 1) % FluxIOState.values().length];
                io.put(d, state);

                // Button Cosmetics
                this.updateButtonCosmetics(b, state);

                // Replace Tag
                FluxIOState.toTag(beTag, io);
                be.fromTag(beTag);
                be.markDirty();
                PacketUtil.sendBlockEntityTagToServer(be);

                System.out.println("End: " + beTag.toString());

            }

        });

    }

    private void updateButtonCosmetics(WColoredButton b, FluxIOState state) {

        b.setBgColor(state.color);
        b.setTooltipCallback(() -> state.lang);

    }

}
