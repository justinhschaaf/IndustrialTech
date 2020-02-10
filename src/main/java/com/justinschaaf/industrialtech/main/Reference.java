package com.justinschaaf.industrialtech.main;

import net.minecraft.util.Identifier;

public class Reference {

    public static final String MODID = "industrial_tech";

    // Textures
    public static class Textures {

        // Energy Bar
        public static final Identifier ENERGY_BAR_BG = createModidIdentifier("textures/gui/energybar_144_bg.png");
        public static final Identifier ENERGY_BAR_FG = createModidIdentifier("textures/gui/energybar_144_fg.png");

        // Progress Bar
        public static final Identifier PROGRESS_BAR_BG = createModidIdentifier("textures/gui/progressbar_144_bg.png");
        public static final Identifier PROGRESS_BAR_FG = createModidIdentifier("textures/gui/progressbar_144_fg.png");

    }

    // Lang
    public static class Texts {

        public static final String ENERGY_UNIT = createModidLangKey("energy_unit");
        public static final String TOOLTIP_ENERGY = createModidLangKey("tooltip_energy");
        public static final String TOOLTIP_PROGRESS = createModidLangKey("tooltip_progress");

    }

    // Blocks
    public static class Blocks {

        // Materials
        public static final Identifier BASIC_MACHINE_FRAME = createModidIdentifier("basic_machine_frame");
        public static final Identifier IMPROVED_MACHINE_FRAME = createModidIdentifier("improved_machine_frame");
        public static final Identifier ADVANCED_MACHINE_FRAME = createModidIdentifier("advanced_machine_frame");

        // Machines
        public static final Identifier COAL_GENERATOR = createModidIdentifier("coal_generator");

    }

    public static class ItemGroups {

        public static final Identifier MATERIALS_GROUP = createModidIdentifier("materials_group");
        public static final Identifier MACHINES_GROUP = createModidIdentifier("machines_group");

    }

    public static class Items {

        // Materials
        public static final Identifier BASIC_PROCESSOR = createModidIdentifier("basic_processor");
        public static final Identifier IMPROVED_PROCESSOR = createModidIdentifier("improved_processor");
        public static final Identifier ADVANCED_PROCESSOR = createModidIdentifier("advanced_processor");

        public static final Identifier BASIC_ENERGY_CRYSTAL = createModidIdentifier("basic_energy_crystal");
        public static final Identifier IMPROVED_ENERGY_CRYSTAL = createModidIdentifier("improved_energy_crystal");
        public static final Identifier ADVANCED_ENERGY_CRYSTAL = createModidIdentifier("advanced_energy_crystal");

        // Machines
        public static final Identifier BASIC_UPGRADE = createModidIdentifier("basic_upgrade");
        public static final Identifier IMPROVED_UPGRADE = createModidIdentifier("improved_upgrade");
        public static final Identifier ADVANCED_UPGRADE = createModidIdentifier("advanced_upgrade");

    }

    public static Identifier createModidIdentifier(String id) {
        return new Identifier(MODID, id);
    }

    public static String createModidLangKey(String id) {
        return MODID + "." + id;
    }

    public static String createModidLangKey(String type, String id) {
        return type + "." + createModidLangKey(id);
    }

}
