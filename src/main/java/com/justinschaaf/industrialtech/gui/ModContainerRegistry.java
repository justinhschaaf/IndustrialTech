package com.justinschaaf.industrialtech.gui;

import com.justinschaaf.industrialtech.main.Reference;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.util.Identifier;

public class ModContainerRegistry {

    public static void registerControllers() {

        // So close, yet so far... :,(
        // Registry.register(Registry.CONTAINER, new Identifier(Reference.MODID, "coal_generator"), (syncId, id, player, buf) -> CoalGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos()))::new);

        ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier(Reference.MODID, "coal_generator"), (syncId, id, player, buf) -> new CoalGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));

    }

    public static void registerScreens() {

        ScreenProviderRegistry.INSTANCE.registerFactory(new Identifier(Reference.MODID, "coal_generator"), (syncId, id, player, buf) -> new CottonInventoryScreen<CoalGeneratorController>(new CoalGeneratorController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));

    }

}
