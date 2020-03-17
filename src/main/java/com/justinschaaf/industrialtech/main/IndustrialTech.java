package com.justinschaaf.industrialtech.main;

import com.justinschaaf.industrialtech.block.ModBlockRegistry;
import com.justinschaaf.industrialtech.block.entity.ModBlockEntityRegistry;
import com.justinschaaf.industrialtech.gui.ModContainerRegistry;
import com.justinschaaf.industrialtech.item.ModItemRegistry;
import com.justinschaaf.industrialtech.network.ModPacketRegistry;
import com.justinschaaf.industrialtech.recipes.ModRecipeRegistry;
import net.fabricmc.api.ModInitializer;

public class IndustrialTech implements ModInitializer {
	
	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Loading IndustrialTech...");
		long startTime = System.currentTimeMillis();

		ModRecipeRegistry.registerRecipes();
		ModPacketRegistry.registerPackets();
		ModBlockRegistry.registerBlocks();
		ModBlockEntityRegistry.registerBlockEntities();
		ModItemRegistry.registerItems();

		ModContainerRegistry.registerControllers();

		System.out.println("Successfully loaded IndustrialTech in " + (System.currentTimeMillis() - startTime) + " miliseconds!");

	}
	
}
