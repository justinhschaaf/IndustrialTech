package com.justinschaaf.industrialtech.block;

import com.justinschaaf.industrialtech.main.Reference;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockRegistry {

    // Materials
    public static Block BASIC_MACHINE_FRAME;
    public static Block IMPROVED_MACHINE_FRAME;
    public static Block ADVANCED_MACHINE_FRAME;

    // Machines
    public static Block COAL_GENERATOR;

    public static void registerBlocks() {

        // Materials
        BASIC_MACHINE_FRAME = register(Reference.Blocks.BASIC_MACHINE_FRAME, new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).nonOpaque().build()));
        IMPROVED_MACHINE_FRAME = register(Reference.Blocks.IMPROVED_MACHINE_FRAME, new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).nonOpaque().build()));
        ADVANCED_MACHINE_FRAME = register(Reference.Blocks.ADVANCED_MACHINE_FRAME, new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).nonOpaque().build()));

        // Machines
        COAL_GENERATOR = register(Reference.Blocks.COAL_GENERATOR, new CoalGenerator(FabricBlockSettings.copy(Blocks.IRON_BLOCK).nonOpaque().build()));

    }

    public static Block register(Identifier identifier, Block block) {
        return Registry.register(Registry.BLOCK, identifier, block);
    }

}
