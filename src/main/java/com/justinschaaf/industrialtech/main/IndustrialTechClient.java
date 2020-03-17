package com.justinschaaf.industrialtech.main;

import com.justinschaaf.industrialtech.block.ModBlockRegistry;
import com.justinschaaf.industrialtech.gui.ModContainerRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class IndustrialTechClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // Transparent blocks
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlockRegistry.CAPACITOR,
                ModBlockRegistry.COAL_GENERATOR
        );

        // Register GUIs
        ModContainerRegistry.registerScreens();

    }

}
