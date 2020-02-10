package com.justinschaaf.industrialtech.main;

import com.justinschaaf.industrialtech.gui.ModContainerRegistry;
import net.fabricmc.api.ClientModInitializer;

public class IndustrialTechClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // Mostly for GUIs
        ModContainerRegistry.registerScreens();

    }

}
