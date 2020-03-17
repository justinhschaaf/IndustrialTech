package com.justinschaaf.industrialtech.gui.widgets;

import net.minecraft.text.Text;

import java.util.function.Supplier;

public interface SuppliedTooltip {

    void setTooltipCallback(Supplier<Text> tooltip);

    Supplier<Text> getTooltipCallback();

    default void withTooltip(Supplier<Text> tooltip) {
        setTooltipCallback(tooltip);
    }

}
