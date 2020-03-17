package com.justinschaaf.industrialtech.network.c2s;

import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class UpdateBlockEntity implements PacketConsumer {

    @Override
    public void accept(PacketContext packetContext, PacketByteBuf packetByteBuf) {

        BlockPos pos = packetByteBuf.readBlockPos();
        CompoundTag tag = packetByteBuf.readCompoundTag();

        packetContext.getTaskQueue().execute(() -> {

            // I would validate the packet information as the wiki instructs, but the method has disintegrated...

            BlockEntity be = packetContext.getPlayer().getEntityWorld().getBlockEntity(pos);

            if (be != null) {

                be.fromTag(tag);
                be.markDirty();

                System.out.println("Server saved BlockEntity: " + tag.toString());

            }

        });

    }

}
