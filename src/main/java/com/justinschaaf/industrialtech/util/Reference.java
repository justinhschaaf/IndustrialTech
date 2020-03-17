package com.justinschaaf.industrialtech.util;

import net.minecraft.util.Identifier;

public class Reference {

    public static final String MODID = "industrial_tech";

    // Textures
    public static class Textures {

        // Energy Bar
        public static final Identifier ENERGY_BAR_BG = createModidIdentifier("textures/gui/energybar_bg.png");
        public static final Identifier ENERGY_BAR_FG = createModidIdentifier("textures/gui/energybar_fg.png");
        public static final Identifier ENERGY_BAR_9_BG = createModidIdentifier("textures/gui/energybar_9_bg.png");
        public static final Identifier ENERGY_BAR_9_FG = createModidIdentifier("textures/gui/energybar_9_fg.png");

        // Progress Bar
        public static final Identifier PROGRESS_BAR_BG = createModidIdentifier("textures/gui/progressbar_9_bg.png");
        public static final Identifier PROGRESS_BAR_FG = createModidIdentifier("textures/gui/progressbar_9_fg.png");

    }

    // Lang
    public static class Texts {

        public static final String ENERGY_UNIT = createModidLangKey("energy_unit");
        public static final String TOOLTIP_ENERGY = createModidLangKey("tooltip_energy");
        public static final String TOOLTIP_PROGRESS = createModidLangKey("tooltip_progress");
        public static final String ENERGY_IO = createModidLangKey("energy_io");

        // Machine IO States
        public static final String MACHINE_IO_DISABLED = createModidLangKey("machine_io_disabled");
        public static final String MACHINE_IO_INPUT = createModidLangKey("machine_io_input");
        public static final String MACHINE_IO_OUTPUT = createModidLangKey("machine_io_output");
        public static final String MACHINE_IO_INPUT_OUTPUT = createModidLangKey("machine_io_input_output");

        public static String createModidLangKey(String id) {
            return MODID + "." + id;
        }

        public static String createModidLangKey(String type, String id) {
            return type + "." + createModidLangKey(id);
        }

        public static String getLangKeyFromIdentifier(Identifier identifier, String type) {
            return type + "." + identifier.getNamespace() + "." + identifier.getPath();
        }

    }

    // Packets
    public static class Packets {

        // Client to Server
        public static final Identifier C2S_UPDATE_BLOCKENTITY = createModidIdentifier("c2s_update_blockentity");

    }

    // Blocks
    public static class Blocks {

        // Materials
        public static final Identifier BASIC_MACHINE_FRAME = createModidIdentifier("basic_machine_frame");
        public static final Identifier IMPROVED_MACHINE_FRAME = createModidIdentifier("improved_machine_frame");
        public static final Identifier ADVANCED_MACHINE_FRAME = createModidIdentifier("advanced_machine_frame");

        // Machines
        public static final Identifier CAPACITOR = createModidIdentifier("capacitor");
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

}
