package com.justinschaaf.industrialtech.item;

import com.justinschaaf.industrialtech.block.ModBlockRegistry;
import com.justinschaaf.industrialtech.main.Reference;
import com.justinschaaf.industrialtech.util.Flux;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItemRegistry {

    // Materials
    public static Item BASIC_MACHINE_FRAME;
    public static Item IMPROVED_MACHINE_FRAME;
    public static Item ADVANCED_MACHINE_FRAME;

    public static BasicItem BASIC_PROCESSOR;
    public static BasicItem IMPROVED_PROCESSOR;
    public static BasicItem ADVANCED_PROCESSOR;

    public static EnergyConsumer BASIC_ENERGY_CRYSTAL;
    public static EnergyConsumer IMPROVED_ENERGY_CRYSTAL;
    public static EnergyConsumer ADVANCED_ENERGY_CRYSTAL;

    // Machines
    public static Upgrade BASIC_UPGRADE;
    public static Upgrade IMPROVED_UPGRADE;
    public static Upgrade ADVANCED_UPGRADE;

    public static Item COAL_GENERATOR;

    public static void registerItems() {

        // Materials
        BASIC_MACHINE_FRAME = register(Reference.Blocks.BASIC_MACHINE_FRAME, new BlockItem(ModBlockRegistry.BASIC_MACHINE_FRAME, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        IMPROVED_MACHINE_FRAME = register(Reference.Blocks.IMPROVED_MACHINE_FRAME, new BlockItem(ModBlockRegistry.IMPROVED_MACHINE_FRAME, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        ADVANCED_MACHINE_FRAME = register(Reference.Blocks.ADVANCED_MACHINE_FRAME, new BlockItem(ModBlockRegistry.ADVANCED_MACHINE_FRAME, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));

        BASIC_PROCESSOR = register(Reference.Items.BASIC_PROCESSOR, new BasicItem(new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        IMPROVED_PROCESSOR = register(Reference.Items.IMPROVED_PROCESSOR, new BasicItem(new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));
        ADVANCED_PROCESSOR = register(Reference.Items.ADVANCED_PROCESSOR, new BasicItem(new Item.Settings().group(ModItemGroups.MATERIALS_GROUP)));

        BASIC_ENERGY_CRYSTAL = register(Reference.Items.BASIC_ENERGY_CRYSTAL, new EnergyConsumer(Flux.BASE_FLUX, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP).rarity(Rarity.RARE)));
        IMPROVED_ENERGY_CRYSTAL = register(Reference.Items.IMPROVED_ENERGY_CRYSTAL, new EnergyConsumer(Flux.BASE_FLUX * 2, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP).rarity(Rarity.RARE)));
        ADVANCED_ENERGY_CRYSTAL = register(Reference.Items.ADVANCED_ENERGY_CRYSTAL, new EnergyConsumer(Flux.BASE_FLUX * 4, new Item.Settings().group(ModItemGroups.MATERIALS_GROUP).rarity(Rarity.RARE)));

        // Machines
        BASIC_UPGRADE = register(Reference.Items.BASIC_UPGRADE, new Upgrade(new Item.Settings().group(ModItemGroups.MACHINES_GROUP).maxCount(1), 0, 1.5));
        IMPROVED_UPGRADE = register(Reference.Items.IMPROVED_UPGRADE, new Upgrade(new Item.Settings().group(ModItemGroups.MACHINES_GROUP).maxCount(1), 1, 2.0));
        ADVANCED_UPGRADE = register(Reference.Items.ADVANCED_UPGRADE, new Upgrade(new Item.Settings().group(ModItemGroups.MACHINES_GROUP).maxCount(1), 2, 4.0));

        COAL_GENERATOR = register(Reference.Blocks.COAL_GENERATOR, new BlockItem(ModBlockRegistry.COAL_GENERATOR, new Item.Settings().group(ModItemGroups.MACHINES_GROUP)));

    }

    public static <T extends Item> T register(Identifier identifier, T item) {
        return Registry.register(Registry.ITEM, identifier, item);
    }

}
