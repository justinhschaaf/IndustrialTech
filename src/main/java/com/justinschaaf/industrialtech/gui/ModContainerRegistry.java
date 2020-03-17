package com.justinschaaf.industrialtech.gui;

import com.justinschaaf.industrialtech.gui.controllers.CapacitorController;
import com.justinschaaf.industrialtech.gui.controllers.CoalGeneratorController;
import com.justinschaaf.industrialtech.util.Reference;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;

public class ModContainerRegistry {

    public static void registerControllers() {

        // So close, yet so far... :,(
        // Registry.register(Registry.CONTAINER, new Identifier(Reference.MODID, "coal_generator"), (syncId, id, player, buf) -> CoalGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos()))::new);

        ContainerProviderRegistry.INSTANCE.registerFactory(Reference.Blocks.CAPACITOR,
                (syncId, id, player, buf) -> new CapacitorController(
                        syncId,
                        player.inventory,
                        BlockContext.create(player.world, buf.readBlockPos())
                )
        );

        ContainerProviderRegistry.INSTANCE.registerFactory(Reference.Blocks.COAL_GENERATOR,
                (syncId, id, player, buf) -> new CoalGeneratorController(
                        syncId,
                        player.inventory,
                        BlockContext.create(player.world, buf.readBlockPos())
                )
        );

    }

    public static void registerScreens() {

        ScreenProviderRegistry.INSTANCE.registerFactory(Reference.Blocks.CAPACITOR,
                (syncId, id, player, buf) -> new CottonInventoryScreen<CapacitorController>(
                        new CapacitorController(
                                syncId,
                                player.inventory,
                                BlockContext.create(player.world, buf.readBlockPos())
                        ), player
                )
        );

        ScreenProviderRegistry.INSTANCE.registerFactory(Reference.Blocks.COAL_GENERATOR,
                (syncId, id, player, buf) -> new CottonInventoryScreen<CoalGeneratorController>(
                        new CoalGeneratorController(
                                syncId,
                                player.inventory,
                                BlockContext.create(player.world, buf.readBlockPos())
                        ), player
                )
        );

    }

}
