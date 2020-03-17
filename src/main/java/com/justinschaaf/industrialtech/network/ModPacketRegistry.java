package com.justinschaaf.industrialtech.network;

import com.justinschaaf.industrialtech.network.c2s.UpdateBlockEntity;
import com.justinschaaf.industrialtech.util.Reference;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class ModPacketRegistry {

    public static void registerPackets() {

        ServerSidePacketRegistry.INSTANCE.register(Reference.Packets.C2S_UPDATE_BLOCKENTITY, new UpdateBlockEntity());

    }

}
