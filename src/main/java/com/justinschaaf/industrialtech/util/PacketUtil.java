package com.justinschaaf.industrialtech.util;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;

public class PacketUtil {

    public static void sendBlockEntityTagToServer(BlockEntity be) {

        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
        packet.writeBlockPos(be.getPos());
        packet.writeCompoundTag(be.toTag(new CompoundTag()));

        ClientSidePacketRegistry.INSTANCE.sendToServer(Reference.Packets.C2S_UPDATE_BLOCKENTITY, packet);

    }

}
