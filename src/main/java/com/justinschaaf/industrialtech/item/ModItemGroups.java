package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.main.Reference;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {

    public static final ItemGroup MATERIALS_GROUP = FabricItemGroupBuilder
            .create(Reference.ItemGroups.MATERIALS_GROUP)
            .icon(() -> new ItemStack(ModItemRegistry.IMPROVED_PROCESSOR))
            .build();

    public static final ItemGroup MACHINES_GROUP = FabricItemGroupBuilder
            .create(Reference.ItemGroups.MACHINES_GROUP)
            .icon(() -> new ItemStack(ModItemRegistry.COAL_GENERATOR))
            .build();

}
