package com.justinschaaf.industrialtech.gui.widgets;

import io.github.cottonmc.cotton.gui.widget.WBar;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Supplier;

public class WITBar extends WBar implements SuppliedTooltip {

    private Supplier<Text> tooltipCallback;

    public WITBar(Identifier bg, Identifier bar, int field, int maxfield) {
        super(bg, bar, field, maxfield);
    }

    public WITBar(Identifier bg, Identifier bar, int field, int maxfield, Direction dir) {
        super(bg, bar, field, maxfield, dir);
    }

    @Override
    public void setTooltipCallback(Supplier<Text> tooltip) {
        this.tooltipCallback = tooltip;
    }

    @Override
    public Supplier<Text> getTooltipCallback() {
        return this.tooltipCallback;
    }

    @Override
    public void addInformation(List<String> information) {
        this.tooltipTextComponent = getTooltipCallback().get();
        super.addInformation(information);
    }

}
