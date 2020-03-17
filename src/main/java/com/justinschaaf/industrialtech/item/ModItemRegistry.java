package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.block.ModBlockRegistry;
import com.justinschaaf.industrialtech.util.Reference;
import com.justinschaaf.industrialtech.util.flux.Flux;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItemRegistry {

    // Materials
    public static Item BASIC_MACHINE_FRAME;
    public static Item IMPROVED_MACHINE_FRAME;
    public static Item ADVANCED_MACHINE_FRAME;

    public static Item BASIC_PROCESSOR;
    public static Item IMPROVED_PROCESSOR;
    public static Item ADVANCED_PROCESSOR;

    public static Item BASIC_ENERGY_CRYSTAL;
    public static Item IMPROVED_ENERGY_CRYSTAL;
    public static Item ADVANCED_ENERGY_CRYSTAL;

    // Machines
    public static Item BASIC_UPGRADE;
    public static Item IMPROVED_UPGRADE;
    public static Item ADVANCED_UPGRADE;

    public static Item CAPACITOR;
    public static Item COAL_GENERATOR;

    public static void registerItems() {

        // Materials
        BASIC_MACHINE_FRAME = register(Reference.Blocks.BASIC_MACHINE_FRAME, new ITBlockItem(ModBlockRegistry.BASIC_MACHINE_FRAME, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        IMPROVED_MACHINE_FRAME = register(Reference.Blocks.IMPROVED_MACHINE_FRAME, new ITBlockItem(ModBlockRegistry.IMPROVED_MACHINE_FRAME, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        ADVANCED_MACHINE_FRAME = register(Reference.Blocks.ADVANCED_MACHINE_FRAME, new ITBlockItem(ModBlockRegistry.ADVANCED_MACHINE_FRAME, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));

        BASIC_PROCESSOR = register(Reference.Items.BASIC_PROCESSOR, new ITItem(new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        IMPROVED_PROCESSOR = register(Reference.Items.IMPROVED_PROCESSOR, new ITItem(new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        ADVANCED_PROCESSOR = register(Reference.Items.ADVANCED_PROCESSOR, new ITItem(new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));

        BASIC_ENERGY_CRYSTAL = register(Reference.Items.BASIC_ENERGY_CRYSTAL, new EnergyConsumer(Flux.BASE_FLUX, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        IMPROVED_ENERGY_CRYSTAL = register(Reference.Items.IMPROVED_ENERGY_CRYSTAL, new EnergyConsumer(Flux.BASE_FLUX * 2, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        ADVANCED_ENERGY_CRYSTAL = register(Reference.Items.ADVANCED_ENERGY_CRYSTAL, new EnergyConsumer(Flux.BASE_FLUX * 4, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));

        // Machines
        BASIC_UPGRADE = register(Reference.Items.BASIC_UPGRADE, new Upgrade(new Item.Settings().group(ModItemGroups.MACHINES_GROUP).maxCount(1), 0, 1.5));
        IMPROVED_UPGRADE = register(Reference.Items.IMPROVED_UPGRADE, new Upgrade(new Item.Settings().group(ModItemGroups.MACHINES_GROUP).maxCount(1), 1, 2.0));
        ADVANCED_UPGRADE = register(Reference.Items.ADVANCED_UPGRADE, new Upgrade(new Item.Settings().group(ModItemGroups.MACHINES_GROUP).maxCount(1), 2, 4.0));

        CAPACITOR = register(Reference.Blocks.CAPACITOR, new ITBlockItem(ModBlockRegistry.CAPACITOR, new Item.Settings().group(ModItemGroups.MACHINES_GROUP)));
        COAL_GENERATOR = register(Reference.Blocks.COAL_GENERATOR, new ITBlockItem(ModBlockRegistry.COAL_GENERATOR, new Item.Settings().group(ModItemGroups.MACHINES_GROUP)));

    }

    public static <T extends Item> T register(Identifier identifier, T item) {
        return Registry.register(Registry.ITEM, identifier, item);
    }

}
