package com.justinschaaf.industrialtech.gui.widgets;

import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class WSuppliedLabel extends WLabel {

    protected Supplier<Text> textSupplier;

    public WSuppliedLabel(Supplier<Text> textSupplier) {
        this(textSupplier, DEFAULT_TEXT_COLOR);
    }

    public WSuppliedLabel(Supplier<Text> textSupplier, int color) {
        super(textSupplier.get(), color);
        this.textSupplier = textSupplier;
    }

    @Override
    public void paintBackground(int x, int y, int mouseX, int mouseY) {
        this.text = this.textSupplier.get();
        super.paintBackground(x, y, mouseX, mouseY);
    }

}
