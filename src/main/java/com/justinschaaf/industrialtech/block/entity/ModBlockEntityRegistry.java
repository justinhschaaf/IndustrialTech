package com.justinschaaf.industrialtech.block.entity;

import com.justinschaaf.industrialtech.block.ModBlockRegistry;
import com.justinschaaf.industrialtech.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class ModBlockEntityRegistry {

    public static BlockEntityType<CoalGeneratorEntity> COAL_GENERATOR;

    public static void registerBlockEntities() {

        COAL_GENERATOR = register(Reference.Blocks.COAL_GENERATOR, CoalGeneratorEntity::new, ModBlockRegistry.COAL_GENERATOR);

    }

    public static <T extends BlockEntity> BlockEntityType<T> register(Identifier identifier, Supplier<? extends T> blockEntity, Block block) {
        return (BlockEntityType<T>) Registry.register(Registry.BLOCK_ENTITY, identifier, BlockEntityType.Builder.create(blockEntity, block).build(null));
    }

}
