package com.justinschaaf.industrialtech.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Direction;

import java.util.HashMap;

public enum FluxIOState {

    DISABLED(0, 8355711, new TranslatableText(Reference.Texts.MACHINE_IO_DISABLED)),
    INPUT(1, 1153535, new TranslatableText(Reference.Texts.MACHINE_IO_INPUT)),
    OUTPUT(2, 16750865, new TranslatableText(Reference.Texts.MACHINE_IO_OUTPUT)),
    INPUT_OUTPUT(3, 7838071, new TranslatableText(Reference.Texts.MACHINE_IO_INPUT_OUTPUT));

    public int id;
    public int color;
    public Text lang;

    FluxIOState(int id, int color, Text lang) {
        this.id = id;
        this.color = color;
        this.lang = lang;
    }

    public static CompoundTag toTag(CompoundTag tag, HashMap<Direction, FluxIOState> io) {

        for (Direction d : io.keySet()) tag.putInt("FluxIOState." + d.asString(), io.get(d).id);

        return tag;

    }

    public static HashMap<Direction, FluxIOState> fromTag(CompoundTag tag) {

        HashMap<Direction, FluxIOState> io = new HashMap<>();

        for (Direction d : Direction.values()) io.put(d, FluxIOState.values()[tag.getInt("FluxIOState." + d.asString())]);

        return io;

    }

}
